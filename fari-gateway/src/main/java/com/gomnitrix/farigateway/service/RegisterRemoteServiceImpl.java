package com.gomnitrix.farigateway.service;

import com.gomnitrix.commons.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

@Service
@EnableFeignClients
public class RegisterRemoteServiceImpl implements RegisterRemoteService {
    @Autowired
    RegisterRemoteService service;

    @Override
    public String register(UserDto userDto) {
        return service.register(userDto);
    }
}
