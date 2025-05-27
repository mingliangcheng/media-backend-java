package com.chen.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.media.common.ShaSignatureUtil;
import com.chen.media.dto.UniverifyLoginDto;
import com.chen.media.pojo.User;
import com.chen.media.service.UserClient;
import com.chen.media.service.UserService;
import com.chen.media.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Object> univerifyLogin(UniverifyLoginDto univerifyLoginDto) throws Exception {
        String secret = "cml&cxuniverify125a#";
        Map<String, String> params = new HashMap<>();
        params.put("openid", univerifyLoginDto.getOpenid());
        params.put("accessToken", univerifyLoginDto.getAccessToken());
        String sign = ShaSignatureUtil.generateHmacSha256Signature(params, secret);
        UniverifyLoginDto newUniverifyLoginDto = new UniverifyLoginDto();
        newUniverifyLoginDto.setOpenid(univerifyLoginDto.getOpenid());
        newUniverifyLoginDto.setAccessToken(univerifyLoginDto.getAccessToken());
        newUniverifyLoginDto.setSign(sign);
        /**
         * "code": 0,
         * "errCode": 0,
         * "errMsg": "",
         * "success": true,
         * "phoneNumber": "19090244893"
         */
        Map<String, Object> result = userClient.getPhoneNumber(newUniverifyLoginDto);
        log.info("云函数返回结果{}", result);
        return result;
    }
}




