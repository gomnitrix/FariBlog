package com.gomnitrix.fariweb.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.GatewayConstConfig;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.exception.PermissionDeniedException;
import com.gomnitrix.commons.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    static final String USER_ID = GatewayConstConfig.HEADER_USER_ID;

    @Autowired
    UserServiceImpl userService;

    @PostMapping()
    public String updateUserInfo(@RequestBody @Validated UserDto userDto, BindingResult errors, @RequestHeader(USER_ID) String userId){
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        if (!userDto.getUid().equals(Long.parseLong(userId))){
            throw new PermissionDeniedException();
        }
        userService.updateUserInfo(userDto);
        return new SuccessResponse.Builder().build().toJson();
    }
}
