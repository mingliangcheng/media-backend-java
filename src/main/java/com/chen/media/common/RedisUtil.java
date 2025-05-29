package com.chen.media.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @className: RedisUtil
 * @Description:
 * @author: 陈明亮
 * @date: 2025/5/29 13:59
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    // 判断是否有key
    public boolean hasKey(String key) {
        if (key == null) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    // 删除key
    public void deleteKey(String key) {
        if (this.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    // 设置value
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 设置value和过期时间，时间单位
    public void setValue(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    // 获取value
    public Object getValue(String key) {
        if (!this.hasKey(key)) {
            return null;
        }
        return redisTemplate.opsForValue().get(key);
    }


}