package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.Response.Response;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.exception.RsaKeyException;
import com.gomnitrix.commons.utils.JsonUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

@Service
@EnableFeignClients
public class AuthRemoteServiceImpl {
    @Autowired
    private AuthRemoteService authRemoteService;

    public String getPublicKey(){
        try {
            String rawResp = authRemoteService.getPublicKey();
            RSAKey key = (RSAKey) JWKSet.parse(rawResp).getKeys().get(0);
            RSAPublicKey publicKey = key.toRSAPublicKey();
            return new String(publicKey.getEncoded());
        }catch (Exception e){
            throw new RsaKeyException();
        }
    }
}
