package com.gomnitrix.commons.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {

    @NotNull(message = "用户名不能为空")
    @Length(min = 4, max = 12, message = "用户名长度必须在4到12之间")
    private String userName;

    @NotNull(message = "密码不能为空")
    @Length(min = 10, max = 18, message = "密码长度必须在10到18位之间")
    @Pattern(regexp = "[0-9]\\d+", message = "密码不符合规范，包含不允许的字符")
    private String passWord;
    //TODO 更改密码格式要求

    @Email
    @NotNull
    private String email;

    private Long uid;

    private Boolean gender;

    private String avatar;

    private LocalDate birthday;

    private String mobile;

    private String validCode;

    private String summary;

    private Integer loginCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String nickName;

    private String source;

    private String qqNumber;

    private String weChat;

    private String occupation;

    /**
     * 评论状态 1:正常 0:禁言
     */
    private Boolean commentStatus;

    private String ipSource;

    private String browser;

    private String os;

    /**
     * 是否开启邮件通知 1:开启 0:关闭
     */
    private Boolean enableEmailNotification;

    /**
     * 用户标签：0：普通用户，1：管理员，2：博主 等
     */
    private Boolean userRole;
}
