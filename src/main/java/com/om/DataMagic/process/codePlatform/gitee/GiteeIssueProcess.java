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
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.GitCodeConstant;
import com.om.DataMagic.infrastructure.pgDB.converter.IssueConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import com.om.DataMagic.infrastructure.pgDB.service.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.RepoService;
import com.om.DataMagic.process.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * issue application service.
 *
 * @author zhaoyan
 * @since 2025-02-06
 */
@Component
public class GiteeIssueProcess implements DriverManager {

    /**
     * gitee service.
     */
    @Autowired
    private GiteeService service;

    /**
     * issue converter.
     */
    @Autowired
    private IssueConverter converter;

    /**
     * repo service.
     */
    @Autowired
    private RepoService repoService;

    /**
     * issue service.
     */
    @Autowired
    private IssueService issueService;

    /**
     * process start .
     */
    @Override
    public void run() {
        List<RepoDO> repoDOList = repoService.list();
        List<IssueDO> issueList = new ArrayList<>();
        for (RepoDO repoDO : repoDOList) {
            issueList.addAll(getIssueList(repoDO));
        }
        if (!issueList.isEmpty()){
            issueService.saveOrUpdateBatch(issueList);
        }
    }

    /**
     * 获取GitCode平台仓库下Issue信息.
     *
     * @param repoDO 仓库信息
     * @return issue信息字符串
     */
    private List<IssueDO> getIssueList(RepoDO repoDO) {
        List<String> issueArrayList = new ArrayList<>();
        int page = 1;
        while (true) {
            String issueInfo = service.getIssueInfo(repoDO.getNamespace(), repoDO.getName(), page);
            if ("{\"message\":\"Not Found Project\"}".equals(issueInfo)
                    || issueInfo.startsWith("IOException retry 3 times failed:")
                    || GitCodeConstant.NULL_ARRAY_RESPONSE.equals(issueInfo)) {
                break;
            }
            page++;
            issueArrayList.add(issueInfo);
        }
        return formatStr(repoDO, issueArrayList);
    }

    /**
     * 转化并组装IssueDO数据.
     *
     * @param repoDO        仓库信息
     * @param issueInfoList issue信息字符串
     * @return issue do 对象
     */
    private List<IssueDO> formatStr(RepoDO repoDO, List<String> issueInfoList) {
        List<ArrayNode> arrayNodeList = issueInfoList.stream().map(
                issueArray -> ObjectMapperUtil.toObject(ArrayNode.class, issueArray)).toList();
        List<IssueDO> issueDOList = new ArrayList<>();
        for (ArrayNode arrayNode : arrayNodeList) {
            issueDOList.addAll(converter.toDOList(arrayNode, repoDO.getNamespace(), CodePlatformEnum.GITEE));
        }
        return issueDOList;
    }
}
