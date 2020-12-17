package com.gomnitrix.commons.dto;

import com.gomnitrix.commons.entity.Blog;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BlogDto {
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

    private String coverUid;

    /**
     * 状态
     */

    private Boolean status;

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

    public Blog toBlog(){
        Blog blog = new Blog();
        //TODO 利用反射等机制将所有不为空的域都转为Blog对象中的域
        blog.setAuthorId(this.authorId);
        blog.setAuthor(this.author);
        blog.setTitle(this.title);
        blog.setContent(this.content);
        return blog;
    }
}
