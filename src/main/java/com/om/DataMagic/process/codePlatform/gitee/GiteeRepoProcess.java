/*
 This project is licensed under the Mulan PSL v2.
 You can use this software according to the terms and conditions of the Mulan PSL v2.
 You may obtain a copy of Mulan PSL v2 at:
     http://license.coscl.org.cn/MulanPSL2
 THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 See the Mulan PSL v2 for more details.
 Created: 2025
*/

package com.om.DataMagic.process.codePlatform.gitee;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.client.codePlatform.gitee.GiteeService;
import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.RepoConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * repo application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteeRepoProcess implements DriverManager {

    /**
     * gitee service.
     */
    @Autowired
    private GiteeService service;

    /**
     * repo converter .
     */
    @Autowired
    private RepoConverter converter;

    /**
     * repo service.
     */
    @Autowired
    private RepoService repoService;

    /**
     * taskconfig .
     */
    @Autowired
    private TaskConfig config;

    /**
     * 执行 拉取并更新指定组织下仓库信息.
     */
    @Override
    public void run() {
        String[] split = config.getOrgs().split(",");
        List<String> repoList = new ArrayList<>();
        for (String orgName : split) {
            repoList.addAll(getRepoList(orgName));
        }
        if (!repoList.isEmpty()){
            saveRepoData(repoList);
        }
    }

    /**
     * 获取gitcode平台组织下仓库信息.
     *
     * @param orgName 组织
     * @return 仓库信息字符串
     */
    private List<String> getRepoList(String orgName) {
        List<String> repoArrayList = new ArrayList<>();
        int page = 1;
        while (true) {
            String repoInfo = service.getRepoInfo(orgName, page);
            if (GitCodeConstant.NULL_ARRAY_RESPONSE.equals(repoInfo)) {
                break;
            }
            page++;
            repoArrayList.add(repoInfo);
        }
        return repoArrayList;
    }

    /**
     * 保存仓库信息到数据库.
     *
     * @param repoList 仓库数据列表
     */
    private void saveRepoData(List<String> repoList) {
        List<ArrayNode> arrayNodeList =
                repoList.stream().map(repoArray -> ObjectMapperUtil.toObject(ArrayNode.class, repoArray)).toList();
        List<RepoDO> repoDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            repoDOList.addAll(converter.toDOList(arrayNode, CodePlatformEnum.GITEE));
        }
        repoService.saveOrUpdateBatch(repoDOList);
    }
}
