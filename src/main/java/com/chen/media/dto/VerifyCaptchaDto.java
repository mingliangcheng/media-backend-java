package com.chen.media.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: VerifyCaptchaDto
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/23 16:29
 */
@Data
public class VerifyCaptchaDto implements Serializable {
    @NotBlank(message = "验证码不能为空")
    String captchaCode;

    @NotBlank(message = "验证码Id不能为空")
    String captchaId;
}