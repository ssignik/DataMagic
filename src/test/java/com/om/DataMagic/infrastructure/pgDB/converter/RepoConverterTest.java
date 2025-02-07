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

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.RepoDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * repo 转换器
 *
 * @author zhaoyan
 * @since 2025-01-16
 */
@ExtendWith(MockitoExtension.class)
public class RepoConverterTest {

    @Test
    @DisplayName("json数组转化为DO数组")
    void testTODOSuccess() {
        RepoConverter repoConverter = new RepoConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getRepoDOListFromAPI());
        Assertions.assertNotNull(arrayNode);

        List<RepoDO> doList = repoConverter.toDOList(arrayNode, CodePlatformEnum.GITCODE);
        Assertions.assertEquals(2, doList.size());
    }

    public String getRepoDOListFromAPI() {
        return "[\n" +
                "    {\n" +
                "        \"id\": 4582361,\n" +
                "        \"full_name\": \"opengaussmirror / openGauss-server-wxl\",\n" +
                "        \"namespace\": {\n" +
                "            \"id\": 4438453,\n" +
                "            \"type\": \"group\",\n" +
                "            \"name\": \"opengaussmirror\",\n" +
                "            \"path\": \"opengaussmirror\",\n" +
                "            \"html_url\": \"https://gitcode.com/opengaussmirror\"\n" +
                "        },\n" +
                "        \"path\": \"openGauss-server-wxl\",\n" +
                "        \"name\": \"openGauss-server-wxl\",\n" +
                "        \"description\": \"\",\n" +
                "        \"internal\": false,\n" +
                "        \"fork\": false,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server-wxl\",\n" +
                "        \"forks_count\": 0,\n" +
                "        \"stargazers_count\": 0,\n" +
                "        \"watchers_count\": 0,\n" +
                "        \"default_branch\": \"main\",\n" +
                "        \"open_issues_count\": 0,\n" +
                "        \"project_creator\": \"wxltrulli\",\n" +
                "        \"pushed_at\": \"2025-01-16T11:31:48.806+08:00\",\n" +
                "        \"created_at\": \"2025-01-16T11:31:48.806+08:00\",\n" +
                "        \"updated_at\": \"2025-01-16T11:31:48.806+08:00\",\n" +
                "        \"status\": \"开始\",\n" +
                "        \"private\": false,\n" +
                "        \"public\": true\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4582301,\n" +
                "        \"full_name\": \"opengaussmirror / openGauss-OM-wxl\",\n" +
                "        \"namespace\": {\n" +
                "            \"id\": 4438453,\n" +
                "            \"type\": \"group\",\n" +
                "            \"name\": \"opengaussmirror\",\n" +
                "            \"path\": \"opengaussmirror\",\n" +
                "            \"html_url\": \"https://gitcode.com/opengaussmirror\"\n" +
                "        },\n" +
                "        \"path\": \"openGauss-OM-wxl\",\n" +
                "        \"name\": \"openGauss-OM-wxl\",\n" +
                "        \"description\": \"\",\n" +
                "        \"internal\": false,\n" +
                "        \"fork\": false,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-OM-wxl\",\n" +
                "        \"forks_count\": 0,\n" +
                "        \"stargazers_count\": 0,\n" +
                "        \"watchers_count\": 0,\n" +
                "        \"default_branch\": \"master\",\n" +
                "        \"open_issues_count\": 0,\n" +
                "        \"project_creator\": \"wxltrulli\",\n" +
                "        \"pushed_at\": \"2025-01-16T11:28:56.820+08:00\",\n" +
                "        \"created_at\": \"2025-01-16T11:28:56.820+08:00\",\n" +
                "        \"updated_at\": \"2025-01-16T11:28:56.820+08:00\",\n" +
                "        \"status\": \"开始\",\n" +
                "        \"language\": \"Python\",\n" +
                "        \"private\": false,\n" +
                "        \"public\": true\n" +
                "    }\n" +
                "]";
    }
}
