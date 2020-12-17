package com.gomnitrix.commons.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import com.gomnitrix.commons.exception.ResourceNotFoundException;
import com.gomnitrix.commons.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
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
    public List<Blog> getBlogsByUserID(long userID, int pageIndex, int pageSize) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", userID)
                .orderByDesc("create_time");
        IPage<Blog> blogs = blogMapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return blogs.getRecords();
    }

    @Override
    public List<Blog> getBlogsInfoByUserID(long userID, int pageIndex, int pageSize) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select("uid", "title", "summary", "create_time")
                .eq("author_id", userID)
                .orderByDesc("create_time");
        IPage<Blog> blogsInfo = blogMapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return blogsInfo.getRecords();
    }

    @Override
    public long addBlog(long userID, BlogDto blog) {
        Blog newBlog = blog.toBlog();
        newBlog.setUid(uuidService.getUid());
        blogMapper.insert(newBlog);
        return newBlog.getUid();
    }
}
