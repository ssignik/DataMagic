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
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * PRConverter 测试类
 *
 * @author zhaoyan
 * @since 2025-01-16
 */
@ExtendWith(MockitoExtension.class)
public class PRConverterTest {

    private static final String ORG_NAME = "opengaussmirror";

    @Test
    @DisplayName("json数组转化为DO数组")
    void testTODOSuccess() {
        PRConverter prConverter = new PRConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getPRListFromAPI());
        Assertions.assertNotNull(arrayNode);

        List<PRDO> doList = prConverter.toDOList(arrayNode, ORG_NAME, CodePlatformEnum.GITCODE);
        Assertions.assertEquals(2, doList.size());
        Assertions.assertEquals(ORG_NAME, doList.get(0).getNamespace());
        Assertions.assertEquals(0, doList.get(0).getAddedLines());
    }

    public String getPRListFromAPI(){
        return "[\n" +
                "    {\n" +
                "        \"number\": 7023,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/merge_requests/7023\",\n" +
                "        \"url\": \"https://gitcode.com/api/v5/repos/opengaussmirror/openGauss-server/pulls/7023\",\n" +
                "        \"prune_branch\": false,\n" +
                "        \"draft\": false,\n" +
                "        \"labels\": [],\n" +
                "        \"user\": {\n" +
                "            \"id\": \"6757fe0cdc1d652f80208c78\",\n" +
                "            \"login\": \"zhubin79\",\n" +
                "            \"name\": \"zhubin79\",\n" +
                "            \"state\": \"active\",\n" +
                "            \"avatar_url\": \"https://cdn-img.gitcode.com/ff/eb/f7f66e3be46a88e92e55688a270d70ca1311586e0867660b4f1ce6c86c1e7c52.png\",\n" +
                "            \"email\": \"\",\n" +
                "            \"name_cn\": \"\",\n" +
                "            \"html_url\": \"https://gitcode.com/zhubin79\"\n" +
                "        },\n" +
                "        \"assignees\": [],\n" +
                "        \"head\": {\n" +
                "            \"label\": \"refs/pull/7023/head\",\n" +
                "            \"ref\": \"refs/pull/7023/head\",\n" +
                "            \"sha\": \"72d9292a86b381e38061ad69b6b50ceab7486028\",\n" +
                "            \"user\": {\n" +
                "                \"id\": \"40\",\n" +
                "                \"login\": \"gitcode-xxm\",\n" +
                "                \"name\": \"xxm\",\n" +
                "                \"state\": \"active\",\n" +
                "                \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                \"email\": \"xiongjiamu@163.com\",\n" +
                "                \"name_cn\": \"\",\n" +
                "                \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "            },\n" +
                "            \"repo\": {\n" +
                "                \"id\": 4441163,\n" +
                "                \"full_path\": \"opengaussmirror/openGauss-server\",\n" +
                "                \"human_name\": \"opengaussmirror / openGauss-server\",\n" +
                "                \"name\": \"openGauss-server\",\n" +
                "                \"path\": \"openGauss-server\",\n" +
                "                \"owner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"assigner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"internal\": false,\n" +
                "                \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server.git\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"base\": {\n" +
                "            \"label\": \"master\",\n" +
                "            \"ref\": \"master\",\n" +
                "            \"sha\": \"392e2ff3aca21e0464405ee799d160411ebeeb3b\",\n" +
                "            \"user\": {\n" +
                "                \"id\": \"40\",\n" +
                "                \"login\": \"gitcode-xxm\",\n" +
                "                \"name\": \"xxm\",\n" +
                "                \"state\": \"active\",\n" +
                "                \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                \"email\": \"xiongjiamu@163.com\",\n" +
                "                \"name_cn\": \"\",\n" +
                "                \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "            },\n" +
                "            \"repo\": {\n" +
                "                \"id\": 4441163,\n" +
                "                \"full_path\": \"opengaussmirror/openGauss-server\",\n" +
                "                \"human_name\": \"opengaussmirror / openGauss-server\",\n" +
                "                \"name\": \"openGauss-server\",\n" +
                "                \"path\": \"openGauss-server\",\n" +
                "                \"owner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"assigner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"internal\": false,\n" +
                "                \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server.git\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"id\": 6403014,\n" +
                "        \"iid\": 7023,\n" +
                "        \"project_id\": 4441163,\n" +
                "        \"title\": \"修复event门禁概率失败情形\",\n" +
                "        \"body\": \"【标题】修复event门禁概率失败情形\\r\\n\\r\\n【实现内容】:\\r\\n- 修复 `event` 门禁用例概率失败问题，略微延长等待时间，等待后台线程执行完成并删除 Job\\r\\n\\r\\n【根因分析】:\\r\\n- 创建 job 并执行会调用后台线程执，默认会删除 job，虽然给定执行job时间为`now()`，但后台线程实际执行不一定及时，导致用例有概率失败\\r\\n\\r\\n【实现方案】:\\r\\n- 略微延长等待时间\\r\\n\\r\\n【关联需求或issue】:\\r\\nhttps://gitee.com/opengauss/openGauss-server/issues/IBECCS\\r\\n\\r\\n【开发自验报告】:\\r\\n1. 请附上自验结果(内容或者截图)\\r\\n2. 是否可以添加fastcheck测试用例，如是，请补充fastcheck用例\\r\\n3. 是否涉及资料修改，如是，在docs仓库补充资料\\r\\n4. 是否考虑升级场景(系统表修改、日志持久化以及修改执行态数据格式)\\r\\n5. 是否考虑在线扩容等扩展场景\\r\\n5. 是否考虑异常场景/并发场景/前向兼容/性能场景\\r\\n6. 是否对其他模块产生影响\\r\\n\\r\\n【其他说明】:\",\n" +
                "        \"state\": \"closed\",\n" +
                "        \"assignees_number\": 0,\n" +
                "        \"created_at\": \"2025-01-15T11:49:11+08:00\",\n" +
                "        \"updated_at\": \"2025-01-15T11:55:21+08:00\",\n" +
                "        \"merged_at\": \"\",\n" +
                "        \"closed_at\": \"\",\n" +
                "        \"target_branch\": \"master\",\n" +
                "        \"source_branch\": \"refs/pull/7023/head\",\n" +
                "        \"source_project_id\": 4441163,\n" +
                "        \"force_remove_source_branch\": false,\n" +
                "        \"web_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/merge_requests/7023\",\n" +
                "        \"merge_request_type\": \"MergeRequest\",\n" +
                "        \"review_mode\": \"approval\",\n" +
                "        \"added_lines\": 0,\n" +
                "        \"removed_lines\": 0,\n" +
                "        \"diff_refs\": {\n" +
                "            \"base_sha\": \"\",\n" +
                "            \"head_sha\": \"72d9292a86b381e38061ad69b6b50ceab7486028\",\n" +
                "            \"start_sha\": \"807fd476ad8fb90280da41cad99bb55da2af3c89\"\n" +
                "        },\n" +
                "        \"notes\": 0,\n" +
                "        \"pipeline_status\": \"\",\n" +
                "        \"pipeline_status_with_code_quality\": \"\",\n" +
                "        \"source_git_url\": \"git@gitcode.com:opengaussmirror/openGauss-server.git\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"number\": 7022,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/merge_requests/7022\",\n" +
                "        \"url\": \"https://gitcode.com/api/v5/repos/opengaussmirror/openGauss-server/pulls/7022\",\n" +
                "        \"prune_branch\": false,\n" +
                "        \"draft\": false,\n" +
                "        \"labels\": [],\n" +
                "        \"user\": {\n" +
                "            \"id\": \"675802cbcd8b9b5233ea78b1\",\n" +
                "            \"login\": \"wangjingyuan8\",\n" +
                "            \"name\": \"wangjingyuan8\",\n" +
                "            \"state\": \"active\",\n" +
                "            \"avatar_url\": \"https://cdn-img.gitcode.com/cf/ed/cf82255e6de34c2f683f7551e349eb752e5d46599b6dfdfe0c1877f980b3937e.jpg\",\n" +
                "            \"email\": \"\",\n" +
                "            \"name_cn\": \"\",\n" +
                "            \"html_url\": \"https://gitcode.com/wangjingyuan8\"\n" +
                "        },\n" +
                "        \"assignees\": [],\n" +
                "        \"head\": {\n" +
                "            \"label\": \"refs/pull/7022/head\",\n" +
                "            \"ref\": \"refs/pull/7022/head\",\n" +
                "            \"sha\": \"746223217fc8bae3d822524b9383cc348978cf2a\",\n" +
                "            \"user\": {\n" +
                "                \"id\": \"40\",\n" +
                "                \"login\": \"gitcode-xxm\",\n" +
                "                \"name\": \"xxm\",\n" +
                "                \"state\": \"active\",\n" +
                "                \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                \"email\": \"xiongjiamu@163.com\",\n" +
                "                \"name_cn\": \"\",\n" +
                "                \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "            },\n" +
                "            \"repo\": {\n" +
                "                \"id\": 4441163,\n" +
                "                \"full_path\": \"opengaussmirror/openGauss-server\",\n" +
                "                \"human_name\": \"opengaussmirror / openGauss-server\",\n" +
                "                \"name\": \"openGauss-server\",\n" +
                "                \"path\": \"openGauss-server\",\n" +
                "                \"owner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"assigner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"internal\": false,\n" +
                "                \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server.git\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"base\": {\n" +
                "            \"label\": \"master\",\n" +
                "            \"ref\": \"master\",\n" +
                "            \"sha\": \"392e2ff3aca21e0464405ee799d160411ebeeb3b\",\n" +
                "            \"user\": {\n" +
                "                \"id\": \"40\",\n" +
                "                \"login\": \"gitcode-xxm\",\n" +
                "                \"name\": \"xxm\",\n" +
                "                \"state\": \"active\",\n" +
                "                \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                \"email\": \"xiongjiamu@163.com\",\n" +
                "                \"name_cn\": \"\",\n" +
                "                \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "            },\n" +
                "            \"repo\": {\n" +
                "                \"id\": 4441163,\n" +
                "                \"full_path\": \"opengaussmirror/openGauss-server\",\n" +
                "                \"human_name\": \"opengaussmirror / openGauss-server\",\n" +
                "                \"name\": \"openGauss-server\",\n" +
                "                \"path\": \"openGauss-server\",\n" +
                "                \"owner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"assigner\": {\n" +
                "                    \"id\": \"40\",\n" +
                "                    \"login\": \"gitcode-xxm\",\n" +
                "                    \"name\": \"xxm\",\n" +
                "                    \"state\": \"active\",\n" +
                "                    \"avatar_url\": \"https://cdn-img.gitcode.com/fa/be/2fa2be6d3ffd01599dbc0a3c71ee9ec4cadb82f63a7a8489187645064ad95e59.png?time=1694709764757\",\n" +
                "                    \"email\": \"xiongjiamu@163.com\",\n" +
                "                    \"name_cn\": \"\",\n" +
                "                    \"html_url\": \"https://gitcode.com/gitcode-xxm\"\n" +
                "                },\n" +
                "                \"internal\": false,\n" +
                "                \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server.git\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"id\": 6403013,\n" +
                "        \"iid\": 7022,\n" +
                "        \"project_id\": 4441163,\n" +
                "        \"title\": \"HNSWPQ对接so包入参删除pqMode\",\n" +
                "        \"body\": \"<!-- 感谢您提交Pull Reqeust -->\\r\\n\\r\\n<!-- 提交说明: 请按照以下的模板提交PR，下列内容均为必填项。如果未补充对应内容，不允许提交PR。 -->\\r\\n\\r\\n【标题】(请简要描述下实现的内容)\\r\\n\\r\\n【实现内容】:\\r\\n删除PQParams中的pqMode，添加到HnswBuildState、PQSearchInfo等openGauss侧的结构体中\\r\\n【根因分析】:\\r\\n\\r\\n【实现方案】:\\r\\n\\r\\n【关联需求或issue】:\\r\\nhttps://e.gitee.com/opengaussorg/dashboard?issue=IAFML0\\r\\n【开发自验报告】:\\r\\n1. 请附上自验结果(内容或者截图)\\r\\n2. 是否可以添加fastcheck测试用例，如是，请补充fastcheck用例\\r\\n3. 是否涉及资料修改，如是，在docs仓库补充资料\\r\\n4. 是否考虑升级场景(系统表修改、日志持久化以及修改执行态数据格式)\\r\\n5. 是否考虑在线扩容等扩展场景\\r\\n5. 是否考虑异常场景/并发场景/前向兼容/性能场景\\r\\n6. 是否对其他模块产生影响\\r\\n\\r\\n【其他说明】:\",\n" +
                "        \"state\": \"closed\",\n" +
                "        \"assignees_number\": 0,\n" +
                "        \"created_at\": \"2025-01-15T10:14:06+08:00\",\n" +
                "        \"updated_at\": \"2025-01-15T11:01:42+08:00\",\n" +
                "        \"merged_at\": \"\",\n" +
                "        \"closed_at\": \"\",\n" +
                "        \"target_branch\": \"master\",\n" +
                "        \"source_branch\": \"refs/pull/7022/head\",\n" +
                "        \"source_project_id\": 4441163,\n" +
                "        \"force_remove_source_branch\": false,\n" +
                "        \"web_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/merge_requests/7022\",\n" +
                "        \"merge_request_type\": \"MergeRequest\",\n" +
                "        \"review_mode\": \"approval\",\n" +
                "        \"added_lines\": 0,\n" +
                "        \"removed_lines\": 0,\n" +
                "        \"diff_refs\": {\n" +
                "            \"base_sha\": \"\",\n" +
                "            \"head_sha\": \"746223217fc8bae3d822524b9383cc348978cf2a\",\n" +
                "            \"start_sha\": \"807fd476ad8fb90280da41cad99bb55da2af3c89\"\n" +
                "        },\n" +
                "        \"notes\": 0,\n" +
                "        \"pipeline_status\": \"\",\n" +
                "        \"pipeline_status_with_code_quality\": \"\",\n" +
                "        \"source_git_url\": \"git@gitcode.com:opengaussmirror/openGauss-server.git\"\n" +
                "    }\n" +
                "]";
    }
}