package com.gomnitrix.commons.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author gomnitrix
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一uid
     */
    @TableId("uid")
    private Long uid;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 性别(1:男2:女)
     */
    @TableField("gender")
    private Boolean gender;

    /**
     * 个人头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 出生年月日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 邮箱验证码
     */
    @TableField("valid_code")
    private String validCode;

    /**
     * 自我简介最多150字
     */
    @TableField("summary")
    private String summary;

    /**
     * 登录次数
     */
    @TableField("login_count")
    private Integer loginCount;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    @TableField("last_login_ip")
    private String lastLoginIp;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 资料来源
     */
    @TableField("source")
    private String source;

    /**
     * 平台uuid
     */
    @TableField("uuid")
    private String uuid;

    /**
     * QQ号
     */
    @TableField("qq_number")
    private String qqNumber;

    /**
     * 微信号
     */
    @TableField("we_chat")
    private String weChat;

    /**
     * 职业
     */
    @TableField("occupation")
    private String occupation;

    /**
     * 评论状态 1:正常 0:禁言
     */
    @TableField("comment_status")
    private Boolean commentStatus;

    /**
     * ip来源
     */
    @TableField("ip_source")
    private String ipSource;

    /**
     * 浏览器
     */
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     */
    @TableField("os")
    private String os;

    /**
     * 是否开启邮件通知 1:开启 0:关闭
     */
    @TableField("enable_email_notification")
    private Boolean enableEmailNotification;

    /**
     * 用户标签：0：普通用户，1：管理员，2：博主 等
     */
    @TableField("user_role")
    private Boolean userRole;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
