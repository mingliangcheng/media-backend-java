package com.chen.media.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.media.common.JwtUtil;
import com.chen.media.common.RedisUtil;
import com.chen.media.common.ShaSignatureUtil;
import com.chen.media.dto.LoginDto;
import com.chen.media.dto.RegisterDto;
import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.pojo.User;
import com.chen.media.result.Result;
import com.chen.media.service.CaptchaService;
import com.chen.media.service.UserClient;
import com.chen.media.service.UserService;
import com.chen.media.mapper.UserMapper;
import com.chen.media.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈明亮
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-05-14 10:13:48
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserClient userClient;

    @Value("${univerify.secret}")
    private String univerifySecret;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result univerifyLogin(UniverifyLoginDto univerifyLoginDto) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("openid", univerifyLoginDto.getOpenid());
        params.put("accessToken", univerifyLoginDto.getAccessToken());
        String sign = ShaSignatureUtil.generateHmacSha256Signature(params, univerifySecret);
        UniverifyLoginDto newUniverifyLoginDto = new UniverifyLoginDto();
        newUniverifyLoginDto.setOpenid(univerifyLoginDto.getOpenid());
        newUniverifyLoginDto.setAccessToken(univerifyLoginDto.getAccessToken());
        newUniverifyLoginDto.setSign(sign);
        /**
         * @description 调用云函数返回数据
         * "code": 0,
         * "errCode": 0,
         * "errMsg": "",
         * "success": true,
         * "phoneNumber": "19090244893"
         * @description 错误结果
         * {success=false, error={code=FunctionBizError, message=非法访问}}
         * {success=false, error={code=FunctionBizError, message=4001:参数错误:openid}}
         */
        Map<String, Object> result = userClient.getPhoneNumber(newUniverifyLoginDto);
        if ((boolean) result.get("success")) {
            String phoneNumber = (String) result.get("phoneNumber");
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("phone", phoneNumber);
            User user = userMapper.selectOne(queryWrapper);
            Map<String, Object> userMap = new HashMap<>();
            // 新增用户，插入数据库
            if (user == null) {
                User user1 = new User();
                user1.setPhone(phoneNumber);
                userMapper.insert(user1);
                String token = jwtUtil.generateToken(user1.getId(), user1.getPhone());
                userMap.put("userId", user1.getId());
                userMap.put("token", token);
            } else {
                String token = jwtUtil.generateToken(user.getId(), user.getPhone());
                userMap.put("userId", user.getId());
                userMap.put("token", token);
            }
            return Result.ok(userMap);
        } else {
            return Result.fail(500, "登录失败");
        }
    }

    /**
     * 手机号密码登录
     *
     * @param loginDto
     * @return
     */
    @Override
    public Result loginByPassword(LoginDto loginDto) throws IOException {
        // 2.校验手机号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone", loginDto.getPhone());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            redisUtil.deleteKey(loginDto.getCaptchaId());
            return Result.fail(500, "手机号不存在");
        }
        // 3.校验密码是否正确
        if (!user.getPassword().equals(DigestUtil.md5Hex(loginDto.getPassword()))) {
            redisUtil.deleteKey(loginDto.getCaptchaId());
            return Result.fail(500, "账号或密码错误");
        }
        // 4.生成token并存入redis
        String token = jwtUtil.generateToken(user.getId(), user.getPhone());
        redisUtil.setValue("user:" + user.getPhone(), token, 365L, TimeUnit.DAYS);
        redisUtil.deleteKey(loginDto.getCaptchaId());
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setAvatarUrl(user.getAvatarUrl());
        loginVo.setGender(user.getGender());
        loginVo.setNickname(user.getNickname());
        loginVo.setSignature(user.getSignature());
        loginVo.setBirthday(user.getBirthday());
        loginVo.setId(user.getId());
        loginVo.setPhone(user.getPhone());
        // 5.返回token
        return Result.ok(loginVo);
    }

    @Override
    public Result register(RegisterDto registerDto) throws IOException {
        // 0校验密码一致
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            redisUtil.deleteKey(registerDto.getCaptchaId());
            return Result.fail(500, "两次密码不一致");
        }
        // 1.校验验证码是否正确
        boolean verifyCaptcha = captchaService.verifyCaptcha(registerDto.getCaptchaCode(), registerDto.getCaptchaId());
        if (!verifyCaptcha) {
            redisUtil.deleteKey(registerDto.getCaptchaId());
            return Result.fail(500, "验证码错误");
        }

        // 2.校验手机号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("phone", registerDto.getPhone());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            redisUtil.deleteKey(registerDto.getCaptchaId());
            return Result.fail(500, "手机号已存在");
        }
        // 3.插入数据库
        User user1 = new User();
        user1.setPhone(registerDto.getPhone());
        user1.setPassword(DigestUtil.md5Hex(registerDto.getPassword()));
        user1.setNickname(registerDto.getNickname());
        userMapper.insert(user1);
        redisUtil.deleteKey(registerDto.getCaptchaId());
        // 4.返回成功
        return Result.ok(null);
    }

}




