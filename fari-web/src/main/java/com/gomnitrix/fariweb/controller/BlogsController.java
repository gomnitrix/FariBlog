package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.dto.BlogDto;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.exception.PermissionDeniedException;
import com.gomnitrix.commons.service.BlogService;
import com.gomnitrix.commons.service.ImageService;
import com.gomnitrix.commons.utils.JsonUtil;
import com.google.gson.JsonObject;
import com.qiniu.util.Auth;
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

    @Autowired
    ImageService imageService;

    @Autowired
    Auth auth;

    static final String USER_ID = GatewayConstConfig.HEADER_USER_ID;

    /**
     * 给前端用户主页的博客card返回摘要信息
     *
     * @param pageIndex 分页查询页面索引
     * @param pageSize  分页查询页面大小
     * @param userId    用户ID
     * @return 标准返回类包装的博客摘要信息
     */
    @GetMapping("/blogsInfo/{pageSize}/{pageIndex}")
    public String getBlogsInfo(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestHeader(USER_ID) String userId) {
        List<JsonObject> blogs = blogService.getBlogsInfoByUserID(Long.parseLong(userId), pageIndex, pageSize,
                "uid", "title", "summary", "create_time", "cover_uid");
//        List<String> covers = imageService.getCoverUrlByBlogs(blogs);
        return new SuccessResponse.Builder().addItem("blogs", blogs)
//                                            .addItem("covers", covers)
                                            .build().toJson();
    }

    @GetMapping("/pageNum/{pageSize}")
    public String getPageNum(@PathVariable int pageSize, @RequestHeader(USER_ID) String userId) {
        return new SuccessResponse.Builder()
                .addItem("pages", blogService.getPagesNum(Long.parseLong(userId), pageSize))
                .build().toJson();
    }

    /**
     * 为当前jwt代表的登录用户插入一条博客
     */
    @PutMapping("/blog")
    public String addBlogs(@RequestBody @Validated BlogDto blogDto, BindingResult errors, @RequestHeader(USER_ID) String userId) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        long blogUid = blogService.addBlog(Long.parseLong(userId), blogDto);
        return new SuccessResponse.Builder().addItem("blogID", blogUid).build().toJson();
    }

    @PostMapping("/blog")
    public String updateBlog(@RequestBody @Validated BlogDto blogDto, BindingResult errors, @RequestHeader(USER_ID) String userId) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        if(!blogService.isMatchUser(Long.parseLong(userId), blogDto.getUid())){
            throw new PermissionDeniedException("Update operation failed, the blog does not belong to your account.");
        }
        blogService.updateBlog(blogDto);
        imageService.deleteBlogCover(blogDto.getUid());
        return new SuccessResponse.Builder().build().toJson();
    }

    @GetMapping("/article/{blogId}")
    public String getArticle(@PathVariable long blogId){
        BlogDto blogDto = blogService.getBlogByUid(blogId);
        String cover = imageService.getImageUrl(blogDto.getCoverUid());
        JsonObject blogJson = JsonUtil.obj2JsonObj(blogDto);
        blogJson.addProperty("cover", cover);
        return new SuccessResponse.Builder().addItem("blog", blogJson).build().toJson();
    }

    @DeleteMapping("/blog/{blogId}")
    public String deleteBlog(@PathVariable long blogId, @RequestHeader("userId") String userId){
        if(!blogService.isMatchUser(Long.parseLong(userId), blogId)){
            throw new PermissionDeniedException("Delete operation failed, the blog does not belong to your account.");
        }
        blogService.deleteBlog(blogId);
        imageService.deleteBlogCover(blogId);
        return new SuccessResponse.Builder().build().toJson();
    }
}
