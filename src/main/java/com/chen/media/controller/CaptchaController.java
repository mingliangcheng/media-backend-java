package com.chen.media.controller;

import com.chen.media.result.Result;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 生成验证码
     */
    @PostMapping("/generate")
    public Result<Map<String, Object>> generateCaptcha(){
        try {
            String captchaText  = captchaProducer.createText();
            String captchaId = UUID.randomUUID().toString();
            BufferedImage captchaImage = captchaProducer.createImage(captchaText);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(captchaImage, "jpg", byteArrayOutputStream);
            String base64Image = "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            log.info("生成验证码uuid: {}, 验证码内容: {}", captchaId, base64Image);
            Map<String, Object> map = new HashMap<>();
            map.put("uuid", captchaId);
            map.put("image", base64Image);
            redisTemplate.opsForValue().set(captchaId, captchaText, 60 * 1);
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成验证码失败: {}", e.getMessage());
            return Result.fail(500, "生成验证码失败");
        }
    }
}