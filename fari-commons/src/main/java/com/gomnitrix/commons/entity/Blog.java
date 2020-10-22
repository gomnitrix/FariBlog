package com.gomnitrix.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 博客表
 * </p>
 *
 * @author gomnitrix
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_blog")
public class Blog extends Model<Blog> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一uid
     */
    @TableId("uid")
    private Long uid;

    /**
     * 作者ID
     */
    @TableField("author_id")
    private Long authorId;

    /**
     * 博客标题
     */
    @TableField("title")
    private String title;

    /**
     * 博客简介
     */
    @TableField("summary")
    private String summary;

    /**
     * 博客内容
     */
    @TableField("content")
    private String content;

    /**
     * 博客点击数
     */
    @TableField("click_count")
    private Integer clickCount;

    /**
     * 博客收藏数
     */
    @TableField("collect_count")
    private Integer collectCount;

    /**
     * 标题图片uid
     */
    @TableField("cover_uid")
    private String coverUid;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否原创（0:不是 1：是）
     */
    @TableField("is_original")
    private String isOriginal;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 博客分类UID
     */
    @TableField("blog_sort_uid")
    private Long blogSortUid;

    /**
     * 是否发布：0：否，1：是
     */
    @TableField("is_publish")
    private String isPublish;

    /**
     * 是否开启评论(0:否 1:是)
     */
    @TableField("open_comment")
    private Boolean openComment;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
