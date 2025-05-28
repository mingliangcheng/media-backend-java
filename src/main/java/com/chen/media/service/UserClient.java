package com.chen.media.service;

import com.chen.media.dto.UniverifyLoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
 * @className: UserClient
 * @Description: TODO 
 *
 * @author: 陈明亮
 * @date: 2025/5/27 14:00
 *
 */
@FeignClient(name = "univerify", url = "https://fc-mp-8961f0d1-7384-4331-9a0e-58e2070b1d88.next.bspapp.com")
public interface UserClient {

    /**
     * 调用云函数，获取手机号
     * @param univerifyLoginDto
     * @return
     *   // {
     *   //   code: 0,
     *   //   message: '',
     *   //   phoneNumber: '138xxxxxxxx'
     *   // }
     */
    @PostMapping("/getPhoneNumber")
    Map<String, Object> getPhoneNumber(@RequestBody UniverifyLoginDto univerifyLoginDto);
}