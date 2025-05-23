package com.chen.media.controller;

import com.chen.media.mapper.UserMapper;
import com.chen.media.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("register")
    public String queryUser (@RequestParam("name") String name, @RequestParam("age") Integer age) {
//        List<User> userList = userMapper.selectList(null);
        return "success" ;
    }

    @PostMapping("addUser")
    public String addUser () {
        return "add";
    }
}
