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

package com.om.DataMagic.process.codePlatform.gitcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.om.DataMagic.client.codePlatform.gitcode.GitCodeService;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.converter.PlatformUserConverter;
import com.om.DataMagic.infrastructure.pgDB.dataobject.CommentDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import com.om.DataMagic.infrastructure.pgDB.service.CommentService;
import com.om.DataMagic.infrastructure.pgDB.service.IssueService;
import com.om.DataMagic.infrastructure.pgDB.service.PRService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PlatformUserDO;
import com.om.DataMagic.infrastructure.pgDB.service.platform.PlatformUserService;
import com.om.DataMagic.process.DriverManager;

@Component
public class GitCodeProcess implements DriverManager {

    /**
     * user service.
     */
    @Autowired
    private PlatformUserService userService;

    /**
     * gitcode service.
     */
    @Autowired
    private GitCodeService service;
    /**
     * pr service.
     */
    @Autowired
    private PRService prService;
    /**
     * issue service.
     */
    @Autowired
    private IssueService issueService;
    /**
     * comment service.
     */
    @Autowired
    private CommentService commentService;
    /**
     * platform user service.
     */
    @Autowired
    private PlatformUserConverter converter;

    /**
     * Logger for logging messages in App class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GitCodeProcess.class);

    /**
     * running start here.
     */
    @Override
    public void run() {
        Set<String> userLoginSet = new HashSet<>();

        List<PRDO> prdoList = prService.list();
        for (PRDO prdo : prdoList) {
            userLoginSet.add(prdo.getUserLogin());
            userLoginSet.add(prdo.getHeadUserLogin());
            userLoginSet.add(prdo.getBaseUserLogin());
            userLoginSet.add(prdo.getBaseOwnerUserLogin());
        }

        List<IssueDO> issueDOList = issueService.list();
        for (IssueDO issueDO : issueDOList) {
            userLoginSet.add(issueDO.getUserLogin());
        }

        List<CommentDO> commentDOList = commentService.list();
        for (CommentDO commentDO : commentDOList) {
            userLoginSet.add(commentDO.getUserLogin());
        }

        userLoginSet = userLoginSet.stream().filter(
                s -> s != null && !s.isEmpty()).collect(Collectors.toSet());
        List<PlatformUserDO> platformUserDOList = getUserList(userLoginSet);

        if (!platformUserDOList.isEmpty()) {
            userService.saveOrUpdateBatch(platformUserDOList);
        }
    }

    /**
     * 获取GitCode平台user信息.
     *
     * @param userLoginSet user login set.
     * @return PlatformUserDO 集合.
     */
    private List<PlatformUserDO> getUserList(Set<String> userLoginSet) {
        List<PlatformUserDO> platformUserList = new ArrayList<>();
        for (String userLogin : userLoginSet) {
            String userInfo = null;
            try {
                userInfo = service.getUserInfo(userLogin);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                continue;
            }
            PlatformUserDO platformUserDO = converter.toDO(ObjectMapperUtil.toJsonNode(userInfo), CodePlatformEnum.GITCODE);
            if (platformUserDO != null) {
                platformUserList.add(platformUserDO);
            }
        }
        return platformUserList;
    }

}
