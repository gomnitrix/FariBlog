package com.gomnitrix.farigateway.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.utils.Base64Util;
import com.gomnitrix.commons.utils.JsonUtil;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = {GeneralConfig.GATEWAY_SHORTR_PATH, GeneralConfig.FARI_BLOG_SHORTR_PATH})
public class AuthClientController {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String secret;

//    @GetMapping(value = GatewayConstConfig.CODE_PATH)
//    public String getTokenByCode(@RequestParam("code") String code) {
//        OkHttpClient httpClient = new OkHttpClient();
//        RequestBody body = new FormBody.Builder()
//                .add("grant_type", "authorization_code")
//                .add("client", clientId)
//                .add("redirect_uri", GatewayConstConfig.HTTP_PREFIX + GeneralConfig.GATEWAY_REDIRECT_URI)
//                .add("code", code)
//                .build();
//        Request request = new Request.Builder()
//                .url(GatewayConstConfig.HTTP_PREFIX + GeneralConfig.AUTH_TOKEN_URI)
//                .post(body)
//                .addHeader("Authorization", "Basic " + Base64Util.base64Encode(clientId + ":" + secret))
//                .build();
//        try {
//            Response response = httpClient.newCall(request).execute();
//            assert response.body() != null;
//            String result = response.body().string();
//            Map tokenMap = JsonUtil.jsonToObject(result, Map.class);
//            String accessToken = tokenMap.get("access_token").toString();
//            return new SuccessResponse.Builder().addItem("access_token", accessToken).build().toJson();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @PostMapping(value = GatewayConstConfig.LOGIN_PATH)
    public String getToken(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord){
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", userName)
                .add("password", passWord)
                .add("scope", "all")
                .build();
        Request request = new Request.Builder()
                .url(GatewayConstConfig.HTTP_PREFIX + GeneralConfig.AUTH_TOKEN_URI)
                .post(body)
                .addHeader("Authorization", "Basic " + Base64Util.base64Encode(clientId + ":" + secret))
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            assert response.body() != null;
            String result = response.body().string();
            Map tokenMap = JsonUtil.jsonToObject(result, Map.class);
            String accessToken = tokenMap.get("access_token").toString();
            return new SuccessResponse.Builder().addItem("access_token", accessToken).build().toJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
