package com.gomnitrix.commons.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import com.gomnitrix.commons.exception.ResourceNotFoundException;
import com.gomnitrix.commons.mapper.BlogConvertMapper;
import com.gomnitrix.commons.mapper.BlogMapper;
import com.gomnitrix.commons.service.BlogService;
import com.gomnitrix.commons.service.ImageService;
import com.gomnitrix.commons.service.UuidService;
import com.gomnitrix.commons.utils.JsonUtil;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Autowired
    UuidService uuidService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    ImageService imageService;

    BlogConvertMapper converter = BlogConvertMapper.INSTANCE;

    @Override
    public BlogDto getBlogByUid(long uid) {
        Blog blog = blogMapper.selectById(uid);
        if (blog == null) {
            throw new ResourceNotFoundException("The requested blog does not exist.");
        }
        return converter.toBlogDto(blog);
    }

    @Override
    public long getPagesNum(long userID, int pageSize) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", userID);
        IPage<Blog> blogs = blogMapper.selectPage(new Page<>(1, pageSize), wrapper);
        return blogs.getPages();
    }

    @Override
    public List<BlogDto> getBlogsByUserID(long userID, int pageIndex, int pageSize) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("author_id", userID)
                .orderByDesc("create_time");
        IPage<Blog> blogs = blogMapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
        return converter.toBlogDtos(blogs.getRecords());
    }

    @Override
    public List<JsonObject> getBlogsInfoByUserID(long userID, int pageIndex, int pageSize, String... columns) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.select(columns)
                .eq("author_id", userID)
                .orderByDesc("create_time");
        IPage<Blog> blogsInfo = blogMapper.selectPage(new Page<>(pageIndex, pageSize), wrapper);
//        blogsInfo.getPages();
        List<Blog> blogs = blogsInfo.getRecords();
        List<JsonObject> jsonBlogs = new ArrayList<>();
        for(Blog blog : blogs){
            JsonObject jsonBlog = JsonUtil.obj2JsonObj(blog);
            jsonBlog.addProperty("cover", imageService.getImageUrl(blog.getCoverUid()));
            jsonBlogs.add(jsonBlog);
        }
        return jsonBlogs;
    }

    @Override
    public long addBlog(long userID, BlogDto blogDto) {
        Blog newBlog = BlogConvertMapper.INSTANCE.fromBlogDto(blogDto);
        newBlog.setUid(uuidService.getUid());
        blogMapper.insert(newBlog);
        imageService.updateCoverBlogUid(blogDto);
        return newBlog.getUid();
    }

    @Override
    public void deleteBlog(long blogId) {
        Blog blog = blogMapper.selectById(blogId);
        int result = blogMapper.deleteById(blogId);
        if (result != 1) {
            throw new ResourceNotFoundException("Delete operation failed, the blog is not exist.");
        }
        imageService.deprecateImage(blog.getCoverUid());
    }

    @Override
    public boolean isMatchUser(long userId, long blogId) {
        BlogDto blogDto = getBlogByUid(blogId);
        return blogDto.getAuthorId().equals(userId);
    }

    @Override
    public void updateBlog(BlogDto blogDto) {
        Blog oldBlog = blogMapper.selectById(blogDto.getUid());
        Blog blog = converter.fromBlogDto(blogDto);
        int result = blogMapper.updateById(blog);
        if (result != 1) {
            throw new ResourceNotFoundException("Update operation failed, the blog is not exist.");
        }
        if(!oldBlog.getCoverUid().equals(blogDto.getCoverUid())){
            imageService.updateCoverBlogUid(blogDto);
            imageService.deprecateImage(oldBlog.getCoverUid());
        }
    }
}
