package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.Response.Response;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

@Service
@EnableFeignClients
public class AuthRemoteServiceImpl {
    @Autowired
    private AuthRemoteService authRemoteService;

    public String getPublicKey() {
        String rawResp = authRemoteService.getPublicKey();
        Response response = JsonUtil.jsonToObject(rawResp, Response.class);
        RSAPublicKey key = (RSAPublicKey) response.getData().get(AuthServerConstConfig.PUBLIC_KEY);
        return new String(key.getEncoded());
    }
}
