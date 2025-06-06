package com.chen.media.service.impl;

import com.chen.media.common.RedisUtil;
import com.chen.media.result.Result;
import com.chen.media.service.CaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className: CaptchaServiceImpl
 * @Description: TODO
 * @author: 陈明亮
 * @date: 2025/5/23 16:16
 */
@Service
@Slf4j
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result getCaptcha() throws IOException {
        String captchaText = captchaProducer.createText();
        String captchaId = UUID.randomUUID().toString();
        BufferedImage captchaImage = captchaProducer.createImage(captchaText);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(captchaImage, "jpg", byteArrayOutputStream);
        String base64Image = "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        log.info("生成验证码uuid: {}, 验证码内容: {}", captchaId, base64Image);
        Map<String, Object> map = new HashMap<>();
        map.put("captchaId", captchaId);
        map.put("image", base64Image);
        redisUtil.setValue(captchaId, captchaText, 60 * 5, TimeUnit.SECONDS);
        return Result.ok(map);
    }

    @Override
    public boolean verifyCaptcha(String code, String captchaId) {
        Object captchaId1 = redisUtil.getValue(captchaId);
        if (captchaId1 == null) {
            return false;
        }
        String captchaText = redisUtil.getValue(captchaId).toString();
        return captchaText.equalsIgnoreCase(code);
    }
}