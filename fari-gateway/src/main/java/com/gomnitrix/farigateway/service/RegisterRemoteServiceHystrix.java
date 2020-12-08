package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class RegisterRemoteServiceHystrix implements RegisterRemoteService {
    @Override
    public String register(UserDto userDto, BindingResult errors) {
        return "remote call timeout";
    }
}
