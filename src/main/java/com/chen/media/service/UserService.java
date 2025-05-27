package com.chen.media.service;

import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
* @author 陈明亮
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-14 10:13:48
*/
public interface UserService extends IService<User> {
    Map<String, Object> univerifyLogin(@RequestBody UniverifyLoginDto univerifyLoginDto) throws Exception;
}
