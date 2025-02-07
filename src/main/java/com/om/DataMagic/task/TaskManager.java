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

package com.om.DataMagic.task;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.om.DataMagic.common.config.TaskConfig;
import com.om.DataMagic.process.DriverManager;

import static java.util.Map.entry;

@Component
public class TaskManager {
    /**
     * 应用上下文.
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * task config.
     */
    @Autowired
    private TaskConfig config;

    /**
     * Map of task mappings.
     */
    public static final Map<String, String> TASK_MAPPING = Map.ofEntries(
            entry("gitcode_user", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeProcess"),
            entry("gitcode_repo", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeRepoProcess"),
            entry("gitcode_pr", "com.om.DataMagic.process.codePlatform.gitcode.GitCodePRProcess"),
            entry("gitcode_issue", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeIssueProcess"),
            entry("gitcode_comment", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeCommentProcess"),
            entry("gitcode_star", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeStarProcess"),
            entry("gitcode_watch", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeWatchProcess"),
            entry("gitcode_fork", "com.om.DataMagic.process.codePlatform.gitcode.GitCodeForkProcess"),
            entry("gitcode_dws_contribute",
                    "com.om.DataMagic.process.codePlatform.gitcode.dws.contribute.GitCodeContributeProcess"),
            entry("gitee_user", "com.om.DataMagic.process.codePlatform.gitee.GiteeProcess"),
            entry("gitee_repo", "com.om.DataMagic.process.codePlatform.gitee.GiteeRepoProcess"),
            entry("gitee_pr", "com.om.DataMagic.process.codePlatform.gitee.GiteePRProcess"),
            entry("gitee_issue", "com.om.DataMagic.process.codePlatform.gitee.GiteeIssueProcess"),
            entry("gitee_comment", "com.om.DataMagic.process.codePlatform.gitee.GiteeCommentProcess"),
            entry("gitee_fork", "com.om.DataMagic.process.codePlatform.gitee.GiteeForkProcess"),
            entry("gitee_star", "com.om.DataMagic.process.codePlatform.gitee.GiteeStarProcess"),
            entry("gitee_watch", "com.om.DataMagic.process.codePlatform.gitee.GiteeWatchProcess")
    );


    /**
     * 日志.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);

    /**
     * 执行任务的入口.
     */
    public void runTasks() {
        String[] driverNames = config.getTasks().split(",");
        try {
            for (String name : driverNames) {
                String className = TASK_MAPPING.get(name);
                Class<?> driverClass = Class.forName(className);
                DriverManager driver = (DriverManager) applicationContext.getBean(driverClass);
                driver.run();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("run exception - {}", e.getMessage());
        }
    }
}
