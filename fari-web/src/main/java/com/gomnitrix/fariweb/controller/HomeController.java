package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
@Slf4j
public class HomeController {
    @Autowired
    BlogService blogService;

    @GetMapping("/user")
    public String getBlogs(@RequestParam int pageIndex, @RequestParam int pageSize){
        SuccessResponse resp = new SuccessResponse.Builder().build();
        resp.setMessage("login success, welcome home!");
        return resp.toJson();
    }
}
