package com.gomnitrix.fariauth.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.AuthServerRespKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

@RestController
public class KeyPairController {

    @Autowired
    private KeyPair oauth2RsaKeyPair;

    private final SuccessResponse.Builder respBuilder = new SuccessResponse.Builder();

    @GetMapping("/rsa/publicKey")
    public String getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) oauth2RsaKeyPair.getPublic();
        return respBuilder
                .addItem(AuthServerRespKey.PUBLIC_KEY, publicKey)
                .build()
                .toJson();
    }
}
