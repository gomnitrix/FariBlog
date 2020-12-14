package com.gomnitrix.commons.service;

import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import com.gomnitrix.commons.exception.ResourceNotFoundException;
import com.gomnitrix.commons.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    UuidService uuidService;
    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog getBlogByUid(long uid) {
        Blog blog = blogMapper.selectById(uid);
        if(blog == null){
            throw new ResourceNotFoundException("The requested blog does not exist");
        }
        return blog;
    }

    @Override
    public long addBlog(BlogDto blog) {
        Blog newBlog =
        return 0;
    }
}
