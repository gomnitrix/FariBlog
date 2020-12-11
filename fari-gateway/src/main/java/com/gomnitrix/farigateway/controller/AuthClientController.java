package com.gomnitrix.farigateway.controller;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.exception.AuthenFailedException;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.utils.Base64Util;
import com.gomnitrix.farigateway.service.RegisterRemoteService;
import com.gomnitrix.farigateway.utils.OkHttpUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = {GeneralConfig.GATEWAY_SHORTR_PATH, GeneralConfig.FARI_BLOG_SHORTR_PATH})
public class AuthClientController {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String secret;
    @Qualifier("registerRemoteServiceImpl")
    @Autowired
    private RegisterRemoteService registerRemoteService;

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(5, 20, TimeUnit.SECONDS))
            .build();

//    @GetMapping(value = GatewayConstConfig.CODE_PATH)
//    public String getTokenByCode(@RequestParam("code") String code) {
//        Map<String, String> body = new HashMap<>();
//        body.put("grant_type", "authorization_code");
//        body.put("client", clientId);
//        body.put("redirect_uri", GatewayConstConfig.HTTP_PREFIX + GeneralConfig.GATEWAY_REDIRECT_URI);
//        body.put("code", code);
//        Map<String, String> header = new HashMap<>();
//        header.put("Authorization", "Basic " + Base64Util.base64Encode(clientId + ":" + secret));
//        Request request = OkHttpUtil.buildPostRequest(body, GatewayConstConfig.HTTP_PREFIX + GeneralConfig.AUTH_TOKEN_URI, header);
//        try {
//            Response response = OkHttpUtil.requestExecute(request);
//            return new SuccessResponse.Builder()
//                    .addItems(OkHttpUtil.getDataFromResp(response))
//                    .build()
//                    .toJson();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @PostMapping(value = GatewayConstConfig.LOGIN_PATH)
    public String getToken(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "password");
        body.put("username", userName);
        body.put("password", passWord);
        body.put("scope", "all");
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Basic " + Base64Util.base64Encode(clientId + ":" + secret));
        Request request = OkHttpUtil.buildPostRequest(body, GatewayConstConfig.HTTP_PREFIX + GeneralConfig.AUTH_TOKEN_URI, header);
        try {
            return OkHttpUtil.requestExecute(request).toString();
        } catch (Exception e) {
            e.printStackTrace();
            //Todo 这里需要调整，下面那个return逻辑不对
        }
        return new ErrorResponse.Builder(new AuthenFailedException()).build().toJson();
    }

    @PostMapping(value = GatewayConstConfig.REGISTER_PATH)
    public String register(@RequestBody @Validated UserDto userDto, BindingResult errors) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            assert error != null;
            return new ErrorResponse.Builder(new InvalidParameterException()).setMessage(error.getDefaultMessage()).build().toJson();
        }
        return registerRemoteService.register(userDto);
    }
}
