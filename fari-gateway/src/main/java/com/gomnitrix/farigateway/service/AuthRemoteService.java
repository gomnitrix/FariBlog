package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.configuration.GeneralConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = GeneralConfig.FARI_AUTH, fallback = AuthRemoteServiceHystrix.class)
public interface AuthRemoteService {
    @GetMapping(AuthServerConstConfig.PUB_KEY_PATH)
    String getPublicKey();
}
