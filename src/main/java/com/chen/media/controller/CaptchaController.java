package com.chen.media.controller;

import com.chen.media.dto.VerifyCaptchaDto;
import com.chen.media.result.Result;
import com.chen.media.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @className: CaptchaController
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/23 10:35
 */
@Slf4j
@RequestMapping("/captcha")
@RestController
public class CaptchaController {
    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CaptchaService captchaService;

    /**
     * 生成验证码
     */
    @PostMapping("/generate")
    public Result generateCaptcha() {
        try {
            return captchaService.getCaptcha();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成验证码失败: {}", e.getMessage());
            return Result.fail(500, "生成验证码失败");
        }
    }
}