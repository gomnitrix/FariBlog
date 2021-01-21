package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.fariweb.configuration.ImageOssConfig;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {
    @Autowired
    private Auth auth;

    @GetMapping("/qiniu/upload/token")
    public String getUploadToken() {
        return new SuccessResponse.Builder()
                .addItem("token", auth.uploadToken(ImageOssConfig.getBucket()))
                .build().toJson();
    }
}
