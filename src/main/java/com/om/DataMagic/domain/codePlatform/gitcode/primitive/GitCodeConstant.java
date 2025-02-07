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

package com.om.DataMagic.domain.codePlatform.gitcode.primitive;

/**
 * gitcode 查询常量.
 *
 * @author zhaoyan
 * @since 2025-01-14
 */
public final class GitCodeConstant {

    private GitCodeConstant() {
    }

    /**
     * 列表查询，每页的数量，最大为 100.
     */
    public static final Integer MAX_PER_PAGE = 100;

    /**
     * 列表查询，最大页数，最大为 100，用于临时处理gitcode-传入任何页数均可返回数据-bug.
     * 当前存在问题接口：issue评论获取接口、fork评论接口
     */
    public static final Integer MAX_PAGE = 10;

    /**
     * 列表查询，空数组响应.
     */
    public static final String NULL_ARRAY_RESPONSE = "[]";

    /**
     * 评论url.
     */
    public static final String COMMENT_URL_PARAM = "#tid-";

    /**
     * gitee评论url拼接-前缀.
     */
    public static final String COMMENT_URL_PREFIX = "#note_";

    /**
     * gitee评论url拼接-后缀.
     */
    public static final String COMMENT_URL_SUFFIX = "_link";

}
