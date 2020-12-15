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
    public Blog getBlogByUid(long uid);

    public List<Blog> getBlogsByUserID(long userID, int pageIndex, int pageSize);

    public long addBlog(BlogDto blog);
}
