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
import com.fasterxml.jackson.databind.JsonNode;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.PlatformUserDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * PlatformUserConverter 测试类.
 *
 * @author zhaoyan
 * @since 2025-02-05
 */
public class PlatformUserConverterTest {
    @Test
    @DisplayName("json数组转化为DO数组")
    void testTODOSuccess() {
        PlatformUserConverter converter = new PlatformUserConverter();
        JsonNode jsonNode = ObjectMapperUtil.toJsonNode(getUserListFromAPI());
        Assertions.assertNotNull(jsonNode);
        PlatformUserDO platformUserDO = converter.toDO(jsonNode, CodePlatformEnum.GITEE);
        Assertions.assertEquals("650d67***139b49b41", platformUserDO.getUserId());
        Assertions.assertEquals("my@deng***mian.com", platformUserDO.getEmail());
    }
    @Test
    @DisplayName("用户不存在")
    void testUnExistSuccess(){
        PlatformUserConverter converter = new PlatformUserConverter();
        JsonNode jsonNode = ObjectMapperUtil.toJsonNode(getUnExistFromAPI());
        Assertions.assertNotNull(jsonNode);
        PlatformUserDO platformUserDO = converter.toDO(jsonNode, CodePlatformEnum.GITEE);
        Assertions.assertNull(platformUserDO);
    }
    public String getUserListFromAPI(){
        return "{" +
                "  \"avatar_url\": \"https://cdn-img.gitcode.com/ec/fb/430ecf07b9ee91bbbbf341d92a36783d06e69086f82ce8cf5a6406f79f1c9cf4.png\",\n" +
                "  \"followers_url\": \"https://api.gitcode.com/api/v5users/deng***mian/followers\",\n" +
                "  \"html_url\": \"https://gitcode.com/deng***mian\",\n" +
                "  \"id\": \"650d67***139b49b41\",\n" +
                "  \"login\": \"deng***mian\",\n" +
                "  \"name\": \"麻凡\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"url\": \"https://api.gitcode.com/api/v5/deng***mian\",\n" +
                "  \"bio\": \"Nacos是由阿里巴巴开源的服务治理中间件，集成了动态服务发现、配置管理和服务元数据管理功能，广泛应用于微服务架构中，简化服务治理过程。\",\n" +
                "  \"blog\": \"https://www.deng***mian.com\",\n" +
                "  \"company\": \"开发者\",\n" +
                "  \"email\": \"my@deng***mian.com\",\n" +
                "  \"followers\": 0,\n" +
                "  \"following\": 6,\n" +
                "  \"top_languages\": [\"Python\", \"Shell\"]\n" +
                "}";
    }
    public String getUnExistFromAPI(){
        return "{\"error_code\":1101,\"error_code_name\":\"USER_AUDIT_FAIL_ERROR\",\"error_message\":\"用户不存在\",\"trace_id\":\"aa2781f63d43c797d9693c57743f86aa\"}";
    }
}