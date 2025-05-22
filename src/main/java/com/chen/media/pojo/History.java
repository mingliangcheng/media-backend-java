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
 * @TableName history
 */
@TableName(value ="history")
@Data
public class History implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 播放类型 1 音乐 2 视频
     */
    private Integer type;

    /**
     * 历史记录类型 1 歌曲 2 视频
     */
    private Integer itemType;

    /**
     * 视频id
     */
    private Long itemId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收藏时间
     */
    private Date collectTime;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}