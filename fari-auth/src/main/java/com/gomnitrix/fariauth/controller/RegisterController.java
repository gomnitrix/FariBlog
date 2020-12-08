package com.gomnitrix.fariauth.controller;

import com.gomnitrix.commons.Response.ErrorResponse;
import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.exception.InvalidParameterException;
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

    @PostMapping(AuthServerConstConfig.REGISTER_PATH)
    public String register(@RequestBody @Validated UserDto userDto, BindingResult errors) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            assert error != null;
            return new ErrorResponse.Builder(new InvalidParameterException()).setMessage(error.getDefaultMessage()).build().toJson();
        }
        userService.register(userDto);
        return new SuccessResponse.Builder().build().toJson();
    }
}
