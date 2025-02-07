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

package com.om.DataMagic.infrastructure.pgDB.converter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PlatformUserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 平台用户 转换器.
 *
 * @author zhaoyan
 * @since 2025-01-23
 */
@Component
public class PlatformUserConverter {

    /**
     * Logger for logging messages in PlatformUserConverter class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformUserConverter.class);

    /**
     * 将user json数据转换成DO 对象.
     * @param userJson user json数据.
     * @return DO 对象.
     */
    public PlatformUserDO toDO(JsonNode userJson, CodePlatformEnum codePlatformEnum) {
        PlatformUserDO userDO = new PlatformUserDO();
        String errorCode = userJson.path("error_code").asText();
        if (!StringUtils.isEmpty(errorCode)) {
            return null;
        }
        userDO.setUserId(userJson.path("id").asText());
        userDO.setUuid(codePlatformEnum.getText() + "-" + userDO.getUserId());
        userDO.setUserLogin(userJson.path("login").asText());
        userDO.setUserName(userJson.path("name").asText());
        userDO.setAvatarUrl(userJson.path("avatar_url").asText());
        userDO.setHtmlUrl(userJson.path("html_url").asText());
        userDO.setType(userJson.path("type").asText());
        userDO.setBio(userJson.path("bio").asText());
        userDO.setBlog(userJson.path("blog").asText());
        userDO.setCompany(userJson.path("company").asText());
        userDO.setWechat(userJson.path("wechat").asText());
        userDO.setQq(userJson.path("qq").asText());
        userDO.setEmail(userJson.path("email").asText());
        userDO.setCodePlatform(codePlatformEnum.getText());
        return userDO;
    }
}
