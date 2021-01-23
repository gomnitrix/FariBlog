package com.gomnitrix.commons.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDto {
    /**
     * 唯一uid
     */
    private Long uid;

    /**
     * 图片文件名
     */
    private String imgName;

    /**
     * 文件地址
     */
    private String imgUrl;

    /**
     * 文件扩展名
     */
    private String extensionName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件类型（头像、封面等）
     */
    private String imgType;

    /**
     * 管理员uid
     */
    private String adminUid;

    /**
     * 用户uid
     */
    private Long userUid;

    /**
     * 博客uid
     */
    private Long blogUid;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 图片存储第三方OSS名称ID（t_oss_name表）
     */
    private Integer sourceId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
