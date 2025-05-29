package com.chen.media.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: LoginVo
 * @Description:
 * @author: 陈明亮
 * @date: 2025/5/29 14:31
 */
@Data
public class LoginVo {
    private Long id;
    private String phone;
    private String nickname;
    private String avatarUrl;
    private String signature;
    private Integer gender;
    private String token;
    private LocalDateTime birthday;
}