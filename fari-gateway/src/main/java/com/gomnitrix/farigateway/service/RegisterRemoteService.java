package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.configuration.GeneralConfig;
import com.gomnitrix.commons.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = GeneralConfig.FARI_AUTH, fallback = RegisterRemoteServiceHystrix.class)
public interface RegisterRemoteService {
    @PostMapping(AuthServerConstConfig.REGISTER_PATH)
    String register(@RequestBody @Validated UserDto userDto);
}
