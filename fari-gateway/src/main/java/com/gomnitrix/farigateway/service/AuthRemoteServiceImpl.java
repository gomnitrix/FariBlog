package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.exception.RsaKeyException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

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
