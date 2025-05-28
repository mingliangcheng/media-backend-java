package com.chen.media.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: UniverifyLoginDto
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/27 14:14
 */
@Data
public class UniverifyLoginDto implements Serializable {
    @NotBlank(message = "openid不能为空")
    String openid;

    @NotBlank(message = "accessToken不能为空")
    String accessToken;

    String sign;
}