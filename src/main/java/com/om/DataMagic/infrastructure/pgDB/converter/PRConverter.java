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
import com.om.DataMagic.infrastructure.pgDB.dataobject.PRDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * pr 转换器.
 *
 * @author zhaoyan
 * @since 2025-01-15
 */
@Component
public class PRConverter {

    /**
     * 将pr json数组转化为DO list.
     *
     * @param arrayNode json数组
     * @param owner     所属者，用于设置namespace值
     * @return DO list
     */
    public List<PRDO> toDOList(ArrayNode arrayNode, String owner, CodePlatformEnum codePlatformEnum) {
        List<PRDO> prDOList = new ArrayList<>();
        for (JsonNode prNode : arrayNode) {
            PRDO prdo = toDO(prNode);
            prdo.setNamespace(owner);
            prdo.setCodePlatform(codePlatformEnum.getText());
            prdo.setUuid(codePlatformEnum.getText() + "-" + prdo.getId());
            prDOList.add(prdo);
        }
        return prDOList;
    }

    /**
     * 将pr json数据转换为DO对象.
     *
     * @param prJson pr json数据
     * @return PROD 对象
     */
    public PRDO toDO(JsonNode prJson) {
        PRDO prDO = new PRDO();
        prDO.setId(prJson.path("id").asText());
        prDO.setNumber(prJson.path("number").asText());
        prDO.setUrl(prJson.path("url").asText());
        prDO.setHtmlUrl(prJson.path("html_url").asText());
        prDO.setTitle(prJson.path("title").asText());
        prDO.setBody(prJson.path("body").asText());
        prDO.setCreatedAt(DateUtil.parse(prJson.path("created_at").asText()));
        prDO.setUpdatedAt(DateUtil.parse(prJson.path("updated_at").asText()));
        prDO.setClosedAt(DateUtil.parse(prJson.path("closed_at").asText()));
        prDO.setMergedAt(DateUtil.parse(prJson.path("merged_at").asText()));
        prDO.setUserLogin(prJson.path("user").path("login").asText());
        prDO.setUserId(prJson.path("user").path("id").asText());
        prDO.setState(prJson.path("state").asText());
        Iterator<JsonNode> assignees = prJson.path("assignees").elements();
        List<String> assigneesUserIdList = new ArrayList<>();
        while (assignees.hasNext()) {
            assigneesUserIdList.add(assignees.next().path("id").asText());
        }
        prDO.setAssigneesUserIds(ObjectMapperUtil.writeValueAsString(assigneesUserIdList));
        Iterator<JsonNode> labels = prJson.path("labels").elements();
        List<String> labelIdList = new ArrayList<>();
        List<String> labelNameList = new ArrayList<>();
        while (labels.hasNext()) {
            JsonNode label = labels.next();
            labelIdList.add(label.path("id").asText());
            labelNameList.add(label.path("name").asText());
        }
        prDO.setLabelsId(ObjectMapperUtil.writeValueAsString(labelIdList));
        prDO.setLabelsName(ObjectMapperUtil.writeValueAsString(labelNameList));
        prDO.setHeadLabel(prJson.path("head").path("label").asText());
        prDO.setHeadRef(prJson.path("head").path("ref").asText());
        prDO.setHeadUserId(prJson.path("head").path("user").path("id").asText());
        prDO.setHeadUserLogin(prJson.path("head").path("user").path("login").asText());
        prDO.setHeadRepoId(prJson.path("head").path("repo").path("id").asText());
        prDO.setHeadRepoPath(prJson.path("head").path("repo").path("path").asText());
        prDO.setHeadRepoFullName(prJson.path("head").path("repo").path("full_path").asText());
        prDO.setBaseLabel(prJson.path("base").path("label").asText());
        prDO.setBaseRef(prJson.path("base").path("ref").asText());
        prDO.setBaseUserId(prJson.path("base").path("user").path("id").asText());
        prDO.setBaseUserLogin(prJson.path("base").path("user").path("login").asText());
        prDO.setBaseRepoId(prJson.path("base").path("repo").path("id").asText());
        prDO.setRepoName(prJson.path("base").path("repo").path("name").asText());
        prDO.setRepoPath(prJson.path("base").path("repo").path("path").asText());
        prDO.setBaseOwnerUserId(prJson.path("base").path("repo").path("owner").path("id").asText());
        prDO.setBaseOwnerUserLogin(prJson.path("base").path("repo").path("owner").path("login").asText());
        prDO.setIsPrivate(prJson.path("base").path("private").asText());
        prDO.setInternal(prJson.path("base").path("internal").asText());
        prDO.setNamespace(prJson.path("base").path("internal").asText());
        prDO.setAddedLines(prJson.path("added_lines").asLong());
        prDO.setRemovedLines(prJson.path("removed_lines").asLong());
        return prDO;
    }
}
