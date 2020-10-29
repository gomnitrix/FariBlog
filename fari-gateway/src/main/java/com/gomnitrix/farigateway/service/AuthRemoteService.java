package com.gomnitrix.farigateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "fari-auth", fallback = AuthRemoteServiceHystrix.class)
public interface AuthRemoteService {
    @GetMapping("/rsa/publicKey")
    String getPublicKey();
}
