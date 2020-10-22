package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class RegisterRemoteServiceHystrix implements RegisterRemoteService {
    @Override
    public String register(UserDto userDto) {
        return "remote call timeout";
    }
}
