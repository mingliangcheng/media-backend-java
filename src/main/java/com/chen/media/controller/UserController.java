package com.chen.media.controller;

import com.chen.media.mapper.UserMapper;
import com.chen.media.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("queryUsers")
    public String queryUser () {
//        List<User> userList = userMapper.selectList(null);
        log.info("queryUsers");
        return "success" ;
    }
}
