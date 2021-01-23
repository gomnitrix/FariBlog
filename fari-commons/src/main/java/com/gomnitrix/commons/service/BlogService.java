package com.gomnitrix.commons.service;

import com.gomnitrix.commons.dto.BlogDto;
import com.google.gson.JsonObject;

import java.util.List;

public interface BlogService {
    /**
     * 通过唯一uid获取对应博客对象
     * @param uid
     * @return
     */
    BlogDto getBlogByUid(long uid);

    long getPagesNum(long userID, int pageSize);

    /**
     * 通过用户ID，以分页查询方式获取对应用户的所有博客
     * @param userID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<BlogDto> getBlogsByUserID(long userID, int pageIndex, int pageSize);

    /**
     * 通过用户uid，以分页查询方式获取用户的所有博客摘要
     * @param userID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<JsonObject> getBlogsInfoByUserID(long userID, int pageIndex, int pageSize, String... columns);

    /**
     * 为用户添加一篇博客
     * @param userID
     * @param blog
     * @return
     */
    long addBlog(long userID, BlogDto blog);

    /**
     * 更新一篇现有的博客
     * @param blogDto
     */
    void updateBlog(BlogDto blogDto);

    /**
     * 删除指定uid对应的博客
     * @param blogId
     * @return
     */
    void deleteBlog(long blogId);

    /**
     * 判断blogId对应的博客是否属于userId对应的用户
     * @param userId
     * @param blogId
     * @return
     */
    boolean isMatchUser(long userId, long blogId);
}
