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
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * IssueConverter 测试类
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@ExtendWith(MockitoExtension.class)
public class IssueConverterTest {

    private static final String ORG_NAME = "opengaussmirror";

    @Test
    @DisplayName("json数组转化为DO数组")
    void testTODOSuccess() {
        IssueConverter issueConverter = new IssueConverter();

        ArrayNode arrayNode = ObjectMapperUtil.toObject(ArrayNode.class, getIssueListFromAPI());
        Assertions.assertNotNull(arrayNode);

        List<IssueDO> doList = issueConverter.toDOList(arrayNode, ORG_NAME, CodePlatformEnum.GITCODE);
        Assertions.assertEquals(2, doList.size());
        Assertions.assertEquals(ORG_NAME, doList.get(0).getNamespace());
    }

    public String getIssueListFromAPI(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 2852706,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/issues/6421\",\n" +
                "        \"number\": \"6421\",\n" +
                "        \"state\": \"open\",\n" +
                "        \"title\": \"服务器非默认ssh端口，在安装集群环境时如何处理\",\n" +
                "        \"body\": \"安全不允许使用默认的ssh端口，分配的服务器是修改了ssh端口的，当我手动将roo和omm用户互信后，并且修改~/.ssh/config文件使得ssh命令默认使用修改后的ssh端口，当我执行./gs_install  -X /data/gausstmp/cluster.xml命令后，出现以下问题如何解决：\\r\\nbegin to create CA cert files\\r\\nThe sslcert will be generated in /data/openGauss/install/app/share/sslcert/om\\r\\n[GAUSS-50216] : Failed to remote copy file [/data/openGauss/install/app/share/sslcert/om/client.crt]. To directory: /data/openGauss/install/app/share/sslcert/om. Command: python3 /data/openGauss/install/tool/script/gspylib/pssh/bin/pscp -r -v -t 1810 -p 300 -H 10.239.16.229 -o /tmp/gauss_output_files_134909_2025-01-16_19:47:38_324793_025 -e /tmp/gauss_error_files_134909_2025-01-16_19:47:38_324793_025 /data/openGauss/install/app/share/sslcert/om/client.crt /data/openGauss/install/app/share/sslcert/om 2>&1 | tee /tmp/gauss_result_134909_2025-01-16_19:47:38_324793_025.log.\\r\\nError:\\r\\n[FAILURE] host-db02:\\r\\nlost connection\\r\\n\",\n" +
                "        \"user\": {\n" +
                "            \"avatar_url\": \"https://cdn-img.gitcode.com/fd/ec/f1d5e62c8991363f51efc1a51d79e9efd6c5e6776edb9983f00ec201a909593c.png\",\n" +
                "            \"html_url\": \"https://gitcode.com/juschui1\",\n" +
                "            \"id\": \"678972dbeb3f4263c28615fe\",\n" +
                "            \"login\": \"juschui1\",\n" +
                "            \"name\": \"juschui1\"\n" +
                "        },\n" +
                "        \"repository\": {\n" +
                "            \"id\": 4441163,\n" +
                "            \"full_name\": \"opengaussmirror/openGauss-server\",\n" +
                "            \"path\": \"openGauss-server\",\n" +
                "            \"name\": \"openGauss-server\",\n" +
                "            \"created_at\": \"2024-12-10T20:15:04+08:00\",\n" +
                "            \"updated_at\": \"2024-12-10T20:15:04+08:00\",\n" +
                "            \"assigner\": {},\n" +
                "            \"paas\": \"\"\n" +
                "        },\n" +
                "        \"created_at\": \"2025-01-16T22:31:09+08:00\",\n" +
                "        \"updated_at\": \"2025-01-16T22:31:15+08:00\",\n" +
                "        \"finished_at\": \"\",\n" +
                "        \"labels\": [],\n" +
                "        \"issue_state\": \"已确认\",\n" +
                "        \"priority\": 0,\n" +
                "        \"issue_type\": \"咨询\",\n" +
                "        \"issue_state_detail\": {\n" +
                "            \"title\": \"已确认\",\n" +
                "            \"serial\": 0,\n" +
                "            \"id\": 161\n" +
                "        },\n" +
                "        \"issue_type_detail\": {\n" +
                "            \"title\": \"咨询\",\n" +
                "            \"id\": 197,\n" +
                "            \"is_system\": false\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2852045,\n" +
                "        \"html_url\": \"https://gitcode.com/opengaussmirror/openGauss-server/issues/6415\",\n" +
                "        \"number\": \"6415\",\n" +
                "        \"state\": \"open\",\n" +
                "        \"title\": \"容器升级从305升级到306，升级报错超时statement timeout\",\n" +
                "        \"body\": \"\\r\\n\\r\\n\\r\\n\\r\\n<!-- #请认真填写以下信息，否则可能由于无法定位，导致issue无法解决而被取消 -->\\r\\n\\r\\n\\r\\n\\r\\n\\r\\n【标题描述】: 容器升级从305升级到306，升级报错超时statement timeout\\r\\n【测试类型：SQL功能/存储功能/接口功能/工具功能/性能/并发/压力长稳/故障注入/安全/资料/编码规范】【测试版本：x.x.x】 问题描述\\r\\n\\r\\n【操作系统和硬件信息】(查询命令: cat /etc/system-release, uname -a):\\r\\n\\r\\n【测试环境】(单机/1主x备x级联备):\\r\\n\\r\\n【被测功能】:\\r\\n\\r\\n【测试类型】:\\r\\n\\r\\n【数据库版本】(查询命令: gaussdb -V):\\r\\n\\r\\n【预置条件】:\\r\\n\\r\\n【操作步骤】(请填写详细的操作步骤):\\r\\n1. xxx\\r\\n2. xxx\\r\\n\\r\\n【预期输出】:\\r\\n\\r\\n【实际输出】:\\r\\n\\r\\n【原因分析】:\\r\\n1. 这个问题的根因\\r\\n2. 问题推断过程\\r\\n3. 还有哪些原因可能造成类似现象\\r\\n4. 该问题是否有临时规避措施\\r\\n5. 问题解决方案\\r\\n6. 预计修复问题时间\\r\\n\\r\\n【日志信息】(请附上日志文件、截图、coredump信息):\\r\\n\\r\\n【测试代码】:\\r\\n\",\n" +
                "        \"user\": {\n" +
                "            \"avatar_url\": \"https://cdn-img.gitcode.com/bb/fa/38b1456b0f78a7aeb35af503fefb9af6c10b780771d9e6bf4070d5263d521c32.jpg\",\n" +
                "            \"html_url\": \"https://gitcode.com/zhang_xubo\",\n" +
                "            \"id\": \"6757fde3d02cfe28cafe18a3\",\n" +
                "            \"login\": \"zhang_xubo\",\n" +
                "            \"name\": \"zhang_xubo\"\n" +
                "        },\n" +
                "        \"repository\": {\n" +
                "            \"id\": 4441163,\n" +
                "            \"full_name\": \"opengaussmirror/openGauss-server\",\n" +
                "            \"path\": \"openGauss-server\",\n" +
                "            \"name\": \"openGauss-server\",\n" +
                "            \"created_at\": \"2024-12-10T20:15:04+08:00\",\n" +
                "            \"updated_at\": \"2024-12-10T20:15:04+08:00\",\n" +
                "            \"assigner\": {},\n" +
                "            \"paas\": \"\"\n" +
                "        },\n" +
                "        \"created_at\": \"2025-01-16T20:02:13+08:00\",\n" +
                "        \"updated_at\": \"2025-01-16T20:02:20+08:00\",\n" +
                "        \"finished_at\": \"\",\n" +
                "        \"labels\": [],\n" +
                "        \"issue_state\": \"待办的\",\n" +
                "        \"priority\": 0,\n" +
                "        \"issue_type\": \"缺陷\",\n" +
                "        \"issue_state_detail\": {\n" +
                "            \"title\": \"待办的\",\n" +
                "            \"serial\": 0,\n" +
                "            \"id\": 157\n" +
                "        },\n" +
                "        \"issue_type_detail\": {\n" +
                "            \"title\": \"缺陷\",\n" +
                "            \"id\": 199,\n" +
                "            \"is_system\": false\n" +
                "        }\n" +
                "    }\n" +
                "]";
    }
}
