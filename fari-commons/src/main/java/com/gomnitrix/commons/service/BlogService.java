package com.gomnitrix.commons.service;

import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;

public interface BlogService {
    /**
     * 通过唯一uid获取对应博客对象
     * @param uid
     * @return
     */
    public Blog getBlogByUid(long uid);

    public long addBlog(BlogDto blog);
}
