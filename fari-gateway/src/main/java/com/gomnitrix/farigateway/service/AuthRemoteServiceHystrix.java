package com.gomnitrix.farigateway.service;

import org.springframework.stereotype.Component;

@Component
public class AuthRemoteServiceHystrix implements AuthRemoteService {
    @Override
    public String getPublicKey() {
        return "remote call timeout";
    }
}
