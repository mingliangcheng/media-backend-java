package com.chen.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.media.pojo.User;
import com.chen.media.service.UserService;
import com.chen.media.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈明亮
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-05-14 10:13:48
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




