package com.gomnitrix.commons.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author gomnitrix
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_image")
public class Image extends Model<Image> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一uid
     */
    @TableId("uid")
    private Long uid;

    /**
     * 图片文件名
     */
    @TableField("img_name")
    private String imgName;

    /**
     * 文件地址
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 文件扩展名
     */
    @TableField("extension_name")
    private String extensionName;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Integer fileSize;

    /**
     * 文件类型（头像、封面等）
     */
    @TableField("img_type")
    private String imgType;

    /**
     * 管理员uid
     */
    @TableField("admin_uid")
    private String adminUid;

    /**
     * 用户uid
     */
    @TableField("user_uid")
    private Long userUid;

    /**
     * 博客uid
     */
    @TableField("blog_uid")
    private Long blogUid;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 图片存储第三方OSS名称ID（t_oss_name表）
     */
    @TableField("source_id")
    private Integer sourceId;

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


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
