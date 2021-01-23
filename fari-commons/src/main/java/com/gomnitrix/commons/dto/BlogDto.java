package com.gomnitrix.commons.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BlogDto {
    /**
     * 唯一uid
     */
    private Long uid;

    /**
     * 作者ID
     */
    @NotNull
    private Long authorId;

    /**
     * 博客标题
     */
    @NotNull
    private String title;

    /**
     * 博客简介
     */

    private String summary;

    /**
     * 博客内容
     */
    @NotNull
    private String content;

    /**
     * 博客点击数
     */

    private Integer clickCount;

    /**
     * 博客收藏数
     */

    private Integer collectCount;

    /**
     * 标题图片uid
     */

    private Long coverUid;

    /**
     * 状态
     */

    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否原创（0:不是 1：是）
     */

    private String isOriginal;

    /**
     * 作者
     */
    @NotNull
    private String author;

    /**
     * 博客分类UID
     */

    private Long blogSortUid;

    /**
     * 是否开启评论(0:否 1:是)
     */
    private Boolean openComment;

    /**
     * 是否发布：0：否，1：是
     */

    private String isPublish;
}
