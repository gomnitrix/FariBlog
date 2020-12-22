package com.gomnitrix.commons.service;

import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;

import java.util.List;

public interface BlogService {
    /**
     * 通过唯一uid获取对应博客对象
     * @param uid
     * @return
     */
    Blog getBlogByUid(long uid);

    long getPagesNum(long userID, int pageSize);

    /**
     * 通过用户ID，以分页查询方式获取对应用户的所有博客
     * @param userID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Blog> getBlogsByUserID(long userID, int pageIndex, int pageSize);

    /**
     * 通过用户uid，以分页查询方式获取用户的所有博客摘要
     * @param userID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Blog> getBlogsInfoByUserID(long userID, int pageIndex, int pageSize, String... columns);

    /**
     * 为用户添加一篇博客
     * @param userID
     * @param blog
     * @return
     */
    long addBlog(long userID, BlogDto blog);
}
