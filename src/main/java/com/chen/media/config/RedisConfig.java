package com.chen.media.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置键（key）的序列化器
        template.setKeySerializer(new StringRedisSerializer());

        // 设置值（value）的序列化器
        template.setValueSerializer(new StringRedisSerializer());

        // 设置哈希（hash）的键和值的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
//        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.java());

        return template;
    }
}
