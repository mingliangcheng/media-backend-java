package com.chen.media.interceptor;

import com.chen.media.common.JwtUtil;
import com.chen.media.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthInterceptor preHandle");
        // 从请求头中获取 Token
        String token = request.getHeader("Authorization");

//         校验 Token 是否存在
//        if (token == null || token.isEmpty()) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//
//            // 创建一个Map来存储响应内容
//            Map<String, Object> responseBody = new HashMap<>();
//            responseBody.put("status", "error");
//            responseBody.put("message", "token过期或不存在");
//
//            // 使用ObjectMapper将Map转换为JSON字符串
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonResponse = objectMapper.writeValueAsString(responseBody);
//
//            // 将JSON字符串写入响应体
//            response.getWriter().write(jsonResponse);
//            return false; // 阻止请求继续执行
//
//        }

        // 校验 Token 是否有效（示例：简单的模拟校验）
//        boolean isValid = jwtUtil.validateToken(token);
//        if (!isValid) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            throw new CustomException(HttpStatus.UNAUTHORIZED.value(), "token过期或不存在");
//        }

        return true;
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
