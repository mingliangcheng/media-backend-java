package com.chen.media.service;

import com.chen.media.dto.LoginDto;
import com.chen.media.dto.RegisterDto;
import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.media.result.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
* @author 陈明亮
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-14 10:13:48
*/
public interface UserService extends IService<User> {
    /**
     * 一键登录
     * @param univerifyLoginDto
     * @return
     * @throws Exception
     */
    Result univerifyLogin(@RequestBody UniverifyLoginDto univerifyLoginDto) throws Exception;

    /**
     * 手机号，密码登录
     * @param phone
     * @param password
     * @return
     */

    Result loginByPassword(LoginDto loginDto) throws IOException;

    Result register(RegisterDto registerDto) throws IOException;
}
