package com.chen.media;

import com.chen.media.common.JwtUtil;
import com.chen.media.common.ShaSignatureUtil;
import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.pojo.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.chen.media.result.Result;
import com.chen.media.service.CaptchaService;
import com.chen.media.service.UserClient;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;


@Slf4j
@SpringBootTest
class MediaApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${univerify.secret}")
    private String univerifySecret;

    @Test
    void testRedis() throws IOException {
        Result captcha = captchaService.getCaptcha();
        log.info("captcha:{}", captcha);
    }

    @Test
    void getRedisValue() throws IOException {
        boolean b = captchaService.verifyCaptcha("dhuD", "2df31cc7-d3c0-4005-98ce-2a08fd09ffab");
        log.info("b:{}", b);
    }

    @Test
    void deleteRedisKey() {
        redisTemplate.delete("92f582c1-7082-4189-919d-a4d40e1d9f34");
    }

    @Test
    void testFeign() throws Exception {
        UniverifyLoginDto univerifyLoginDto = new UniverifyLoginDto();
        univerifyLoginDto.setOpenid("123456");
        univerifyLoginDto.setAccessToken("123456");
        Map<String, String> params = new HashMap<>();
        params.put("openid", univerifyLoginDto.getOpenid());
        params.put("accessToken", univerifyLoginDto.getAccessToken());
        String sign = ShaSignatureUtil.generateHmacSha256Signature(params, univerifySecret);
        univerifyLoginDto.setSign(sign);
        Map<String, Object> map = userClient.getPhoneNumber(univerifyLoginDto);
        log.info("phoneNumber:{}", map);
    }

    @Test
    void testJwt() throws Exception {
        String token = jwtUtil.generateToken(1L, "15793691845");
        log.info("token:{}", token);
    }

    @Test
    void parseJwt() {
        Claims claims = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMT0dJTl9VU0VSIiwiZXhwIjoxNzc5OTQ5MTEzLCJ1c2VySWQiOjEsInVzZXJuYW1lIjoiMTU3OTM2OTE4NDUifQ.T_u80rdpHwf2uIFUaZsq7eGxP1-J_K0IGhSPOpw6Gx8");
        log.info("claims:{}", claims.get("username"));
    }

    @Test
    void testTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(formatter);
        log.info("format:{}", format);
        log.info("当前时间{}", LocalDateTime.now());
    }

}
