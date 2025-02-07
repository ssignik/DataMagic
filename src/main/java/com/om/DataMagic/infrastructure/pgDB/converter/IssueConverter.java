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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.om.DataMagic.common.util.DateUtil;
import com.om.DataMagic.common.util.ObjectMapperUtil;
import com.om.DataMagic.domain.codePlatform.gitcode.primitive.CodePlatformEnum;
import com.om.DataMagic.infrastructure.pgDB.dataobject.IssueDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * issue 转换器.
 *
 * @author zhaoyan
 * @since 2025-01-17
 */
@Component
public class IssueConverter {

    /**
     * 将issue json数组转化为DO list.
     *
     * @param arrayNode json数组
     * @param owner     所属者，用于设置namespace值
     * @return DO list
     */
    public List<IssueDO> toDOList(ArrayNode arrayNode, String owner, CodePlatformEnum codePlatformEnum) {
        List<IssueDO> issueDOList = new ArrayList<>();
        for (JsonNode issueNode : arrayNode) {
            IssueDO issueDO = toDO(issueNode);
            issueDO.setNamespace(owner);
            issueDO.setCodePlatform(codePlatformEnum.getText());
            issueDO.setUuid(codePlatformEnum.getText() + "-" + issueDO.getId());
            issueDOList.add(issueDO);
        }
        return issueDOList;
    }

    /**
     * @param issueJson json.
     * @return IssueDO
     */
    public IssueDO toDO(JsonNode issueJson) {
        IssueDO issueDO = new IssueDO();
        issueDO.setId(issueJson.path("id").asText());
        issueDO.setNumber(issueJson.path("number").asText());
        issueDO.setUrl(issueJson.path("url").asText());
        issueDO.setHtmlUrl(issueJson.path("html_url").asText());
        issueDO.setTitle(issueJson.path("title").asText());
        issueDO.setBody(issueJson.path("body").asText());
        issueDO.setCreatedAt(DateUtil.parse(issueJson.path("created_at").asText()));
        issueDO.setUpdatedAt(DateUtil.parse(issueJson.path("updated_at").asText()));
        issueDO.setClosedAt(DateUtil.parse(issueJson.path("finished_at").asText()));
        issueDO.setUserId(issueJson.path("user").path("id").asText());
        issueDO.setUserLogin(issueJson.path("user").path("login").asText());
        issueDO.setState(issueJson.path("state").asText());
        issueDO.setAssigneesUserIds(issueJson.path("assignee").path("id").asText());
        Iterator<JsonNode> labels = issueJson.path("labels").elements();
        List<String> labelIdList = new ArrayList<>();
        List<String> labelNameList = new ArrayList<>();
        while (labels.hasNext()) {
            JsonNode label = labels.next();
            labelIdList.add(label.path("id").asText());
            labelNameList.add(label.path("name").asText());
        }
        issueDO.setLabelsId(ObjectMapperUtil.writeValueAsString(labelIdList));
        issueDO.setLabelsName(ObjectMapperUtil.writeValueAsString(labelNameList));
        issueDO.setIssueState(issueJson.path("issue_state").asText());
        issueDO.setIssueType(issueJson.path("issue_type").asText());
        issueDO.setRepoId(issueJson.path("repository").path("id").asText());
        issueDO.setRepoName(issueJson.path("repository").path("name").asText());
        issueDO.setRepoPath(issueJson.path("repository").path("path").asText());
        issueDO.setPriority(issueJson.path("priority").asText());
        issueDO.setProgram(issueJson.path("program").asText());
        issueDO.setSecurityHole(issueJson.path("security_hole").asText());
        return issueDO;
    }

}
