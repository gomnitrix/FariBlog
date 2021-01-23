package com.gomnitrix.fariauth.controller;

import com.gomnitrix.commons.Response.SuccessResponse;
import com.gomnitrix.commons.configuration.AuthServerConstConfig;
import com.gomnitrix.commons.dto.UserDto;
import com.gomnitrix.commons.exception.InvalidParameterException;
import com.gomnitrix.commons.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class RegisterController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping(AuthServerConstConfig.REGISTER_PATH)
    public String register(@RequestBody @Validated UserDto userDto, BindingResult errors) {
        if (errors.hasErrors()) {
            FieldError error = errors.getFieldError();
            throw new InvalidParameterException(Objects.requireNonNull(error).getDefaultMessage());
        }
        userService.register(userDto);
        return new SuccessResponse.Builder().build().toJson();
    }
}
