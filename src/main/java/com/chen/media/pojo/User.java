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
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像id
     */
    private String avatarUrl;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 状态 0 禁用 1 启用
     */
    private Integer status;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}