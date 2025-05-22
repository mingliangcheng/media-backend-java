package com.chen.media.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName picture
 */
@TableName(value ="picture")
@Data
public class Picture implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String pictureUrl;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer isDeleted;

    /**
     * 所属对象类型 1 音乐 2 视频
     */
    private Integer itemType;

    /**
     * 所属对象id
     */
    private Long itemId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}