package com.chen.media.service;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @className: CaptchaService
 * @Description: TODO 
 *
 * @author: 陈明亮
 * @date: 2025/5/23 16:15
 *
 */
public interface CaptchaService {
    /**
     * 获取验证码
     * @return
     * @throws IOException
     */
    Map<String, Object> getCaptcha() throws IOException;

    /**
     * 验证验证码
     * @param code
     * @param captchaId
     * @return
     */
    boolean verifyCaptcha(String code, String captchaId) throws IOException;
}