package com.chen.media;

import com.chen.media.pojo.User;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.chen.media.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@Slf4j
@SpringBootTest
class MediaApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CaptchaService captchaService;

    @Test
    void testRedis() throws IOException {
        Map<String, Object> captcha = captchaService.getCaptcha();
        log.info("captcha:{}", captcha);
    }

    @Test
    void getRedisValue() throws IOException {
        boolean b = captchaService.verifyCaptcha("dhuD", "2df31cc7-d3c0-4005-98ce-2a08fd09ffab");
        log.info("b:{}", b);
    }

}
