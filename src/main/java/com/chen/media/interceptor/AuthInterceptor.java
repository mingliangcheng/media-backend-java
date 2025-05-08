package com.chen.media.interceptor;
import com.chen.media.common.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthInterceptor preHandle");
        // 从请求头中获取 Token
        String token = request.getHeader("Authorization");

        // 校验 Token 是否存在
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 返回 401 状态码
            response.getWriter().write("Missing or invalid token");
            return false; // 中断请求
        }

        // 校验 Token 是否有效（示例：简单的模拟校验）
        boolean isValid = jwtUtil.validateToken(token);
        if (!isValid) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 返回 403 状态码
            response.getWriter().write("Invalid token");
            return false; // 中断请求
        }

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
