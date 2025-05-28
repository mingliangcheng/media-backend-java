package com.chen.media.interceptor;

import com.chen.media.common.JwtUtil;
import com.chen.media.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthInterceptor preHandle");
        // 从请求头中获取 Token
        String authHeader = request.getHeader("Authorization");

        // 校验 Token 是否存在
        if (authHeader == null || authHeader.isEmpty()) {
            response.setStatus(SC_UNAUTHORIZED);
            throw new CustomException(SC_UNAUTHORIZED, "token过期或不存在");
        }

        if  (authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // 去掉"Bearer "前缀
            // 校验 Token 是否有效（示例：简单的模拟校验）
            boolean isValid = jwtUtil.validateToken(token);
            if (!isValid) {
                response.setStatus(SC_UNAUTHORIZED);
                throw new CustomException(SC_UNAUTHORIZED, "token过期或不存在");
            }
            Claims claims = jwtUtil.parseToken(token);
            if (claims == null) {
                response.setStatus(SC_UNAUTHORIZED);
                throw new CustomException(SC_UNAUTHORIZED, "token过期或不存在");
            } else {
                Object username = claims.get("username");
                Object o = redisTemplate.opsForValue().get("user: " + username);
                if (o == null) {
                    response.setStatus(SC_UNAUTHORIZED);
                    throw new CustomException(SC_UNAUTHORIZED, "token过期或不存在");
                } else {
                    return true;
                }
            }
        } else {
            response.setStatus(SC_UNAUTHORIZED);
            throw new CustomException(SC_UNAUTHORIZED, "token过期或不存在");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
