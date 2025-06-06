package com.chen.media.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.chen.media.controller.*.*(..))")
    public void logMethodCall(org.aspectj.lang.JoinPoint joinPoint) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // 打印请求方式和 URL
        log.info("Request Method: {}, URL: {}", request.getMethod(), request.getRequestURL());

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            parameterMap.forEach((key, values) -> {
                log.info("GET Parameter: {} = {}", key, String.join(", ", values));
            });
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (request.getContentType() != null && request.getContentType().contains("application/json")) {
                ContentCachingRequestWrapper cachingRequest = new ContentCachingRequestWrapper(request);
                byte[] buf = cachingRequest.getContentAsByteArray();
                if (buf.length > 0) {
                    String requestBody = new String(buf, 0, buf.length, cachingRequest.getCharacterEncoding());
                    log.info("JSON Body Parameters: {}", requestBody);
                }
            } else {
                // 表单参数
                Map<String, String[]> parameterMap = request.getParameterMap();

                String parameters = parameterMap.entrySet().stream()
                        .map(entry -> entry.getKey() + " = " + String.join(", ", entry.getValue()))
                        .collect(Collectors.joining(", "));

                log.info("POST Parameters: {}", parameters);
            }


        }
    }

    @AfterReturning(pointcut = "execution(* com.chen.media.controller.*.*(..))", returning = "result")
    public void logMethodReturn(Object result) {
        // 记录返回值
        log.info("响应结果: {}", result);
    }
}