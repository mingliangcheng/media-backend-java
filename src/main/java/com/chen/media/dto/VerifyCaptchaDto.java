package com.chen.media.dto;

import lombok.Data;

/**
 * @className: VerifyCaptchaDto
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/23 16:29
 */
@Data
public class VerifyCaptchaDto {
    String captchaCode;
    String captchaId;
}