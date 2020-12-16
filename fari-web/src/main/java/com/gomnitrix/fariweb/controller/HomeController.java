package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import com.gomnitrix.commons.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/index")
@Slf4j
public class HomeController {
    @Autowired
    BlogService blogService;

    SuccessResponse.Builder respBuilder = new SuccessResponse.Builder();

    @GetMapping("/blog")
    public String getBlogs(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestHeader("userId") String userId){
        List<Blog> blogs = blogService.getBlogsByUserID(Long.parseLong(userId), pageIndex, pageSize);
        return respBuilder.addItem("blogs", blogs).build().toJson();
    }

    @PostMapping("/blog")
    public String addBlogs(@RequestBody BlogDto blogDto){
        blogService.addBlog(blogDto);
        return respBuilder.build().toJson();
    }
}
