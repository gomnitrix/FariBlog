package com.gomnitrix.fariauth.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.utils.JsonUtil;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
public class KeyPairController {

    @Autowired
    private KeyPair oauth2RsaKeyPair;

    private final SuccessResponse.Builder respBuilder = new SuccessResponse.Builder();

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) oauth2RsaKeyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
