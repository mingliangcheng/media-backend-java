package com.chen.media.controller;

import com.chen.media.dto.LoginDto;
import com.chen.media.dto.RegisterDto;
import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.dto.VerifyCaptchaDto;
import com.chen.media.mapper.UserMapper;
import com.chen.media.pojo.User;
import com.chen.media.result.Result;
import com.chen.media.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * @description 一键登录
     */
    @PostMapping("univerify")
    public Result univerifyLogin(@Valid @RequestBody UniverifyLoginDto univerifyLoginDto) throws Exception {
        log.info("请求参数：{}", univerifyLoginDto.toString());
        return userService.univerifyLogin(univerifyLoginDto);
    }

    /**
     * @description 手机号密码登录
     */
    @PostMapping("loginByPassword")
    public Result loginByPassword(@Valid @RequestBody LoginDto loginDto) throws Exception {
        log.info("请求参数：{}", loginDto.toString());
        return userService.loginByPassword(loginDto);
    }

    /**
     * @description 账号注册
     */
    @PostMapping("register")
    public Result register(@Valid @RequestBody RegisterDto registerDto) throws Exception {
        log.info("请求参数：{}", registerDto.toString());
        return userService.register(registerDto);
    }
}
