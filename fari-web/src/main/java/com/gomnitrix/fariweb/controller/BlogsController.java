package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.entity.Blog;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/index")
@Slf4j
public class BlogsController {
    @Autowired
    BlogService blogService;

    SuccessResponse.Builder respBuilder = new SuccessResponse.Builder();

    @GetMapping("/blogsInfo/{pageSize}/{pageIndex}")
    public String getBlogsInfo(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestHeader("userId") String userId){
        List<Blog> blogs = blogService.getBlogsInfoByUserID(Long.parseLong(userId), pageIndex, pageSize);
        return respBuilder.addItem("blogs", blogs).build().toJson();
    }

    @PostMapping("/blog")
    public String addBlogs(@RequestBody @Validated BlogDto blogDto, BindingResult errors, @RequestHeader("userId") String userId){
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        long blogUid = blogService.addBlog(Long.parseLong(userId), blogDto);
        return respBuilder.addItem("blogID", blogUid).build().toJson();
    }
}
