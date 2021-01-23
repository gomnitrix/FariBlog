package com.gomnitrix.commons.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("t_oss_name")
public class OssName extends Model<OssName> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 图片存储OSS第三方名称
     */
    @TableField("source")
    private String source;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
