package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.configuration.GeneralConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = GeneralConfig.FARI_AUTH, fallback = AuthRemoteServiceHystrix.class)
public interface RegisterRemoteService {

}
