package com.gomnitrix.farigateway.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.utils.Base64Util;
import com.gomnitrix.commons.utils.JsonUtil;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = GatewayConstConfig.FARI_BLOG)
public class AuthClientController {
    @GetMapping(value = GatewayConstConfig.CODE_PATH)
    public String codeToToken(String code){
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "authorization_code")
                .add("client", GatewayConstConfig.CLIENT_ID)
                .add("redirect_uri",GatewayConstConfig.REDIRECT_URI)
                .add("code", code)
                .build();
        Request request = new Request.Builder()
                .url(GatewayConstConfig.AUTH_SERVER+ AuthServerConstConfig.TOKEN_PATH)
                .post(body)
                .addHeader("Authorization", "Basic "+ Base64Util.base64Encode(GatewayConstConfig.CLIENT_ID+":"+GatewayConstConfig.CLIENT_SECRET))
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
