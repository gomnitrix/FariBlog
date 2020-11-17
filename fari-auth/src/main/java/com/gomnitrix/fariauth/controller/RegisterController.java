package com.gomnitrix.fariauth.controller;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RegisterController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SuccessResponse.Builder respBuilder;
    @Autowired
    ErrorResponse.Builder errorBuilder;

    @PostMapping("/register")
    public String register(@RequestBody @Validated UserDto userDto, BindingResult errors) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            assert error != null;
            return errorBuilder.setMessage(error.getDefaultMessage()).build().toJson();
        }
        userService.register(userDto);
        return respBuilder.build().toJson();
    }
}
