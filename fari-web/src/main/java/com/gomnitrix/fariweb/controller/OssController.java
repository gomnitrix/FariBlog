package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.dto.ImageDto;
import com.gomnitrix.commons.exception.AuthenFailedException;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.service.Impl.ImageServiceImpl;
import com.gomnitrix.commons.utils.JsonUtil;
import com.gomnitrix.fariweb.configuration.ImageOssConfig;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {
    @Autowired
    private Auth auth;

    @Autowired
    private ImageServiceImpl imageService;

    @Value("${qiniu.bucket}")
    private String bucket;

    private final StringMap policy = ImageOssConfig.getPolicy();

    private final long expireSeconds = ImageOssConfig.getExpireSeconds();

    @GetMapping("/qiniu/upload/token")
    public String getUploadToken() {

        String token = auth.uploadToken(bucket, null, expireSeconds, policy);
        return new SuccessResponse.Builder()
                .addItem("token", token)
                .build().toJson();
    }

    @PostMapping("/qiniu/callbackPoint")
    public String qiniuCallbackPoint(@RequestBody @Validated ImageDto imageDto, BindingResult errors, @RequestHeader("Authorization") String authorization){
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        String callbackUrl = ImageOssConfig.callBackUrl;
        if(!auth.isValidCallback(authorization, callbackUrl, StringUtils.utf8Bytes(JsonUtil.objectToJson(imageDto)), callbackUrl)){
            //TODO remove image
            return new ErrorResponse.Builder(new AuthenFailedException("QiniuCloud callback authentication failed.")).build().toJson();
        }
        imageService.saveImage(imageDto);
        return new SuccessResponse.Builder().addItem("image", imageDto).build().toJson();
    }
}
