package com.chen.media.controller;

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

    @GetMapping("register")
    public String queryUser (@RequestParam("name") String name, @RequestParam("age") Integer age) {
//        List<User> userList = userMapper.selectList(null);
        return "success" ;
    }

    @PostMapping("addUser")
    public String addUser (@RequestBody VerifyCaptchaDto verifyCaptchaDto) {
        return "add";
    }

    /**
     * 一键登录
     */
    @PostMapping("univerify")
    public Result<Map<String, Object>> univerifyLogin (@Valid @RequestBody UniverifyLoginDto univerifyLoginDto) throws Exception {
        Map<String, Object> loginResult = userService.univerifyLogin(univerifyLoginDto);
        return Result.ok(loginResult);
    }
}
