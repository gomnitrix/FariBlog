package com.gomnitrix.commons.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    @NotNull(message = "用户名不能为空")
    @Length(min = 4, max = 12, message = "用户名长度必须在4到12之间")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Length(min = 10, max = 18, message = "密码长度必须在10到18位之间")
    @Pattern(regexp = "[0-9]\\d+", message = "密码不符合规范")
    private String passWord;

    @Email
    @NotNull
    private String email;
}
