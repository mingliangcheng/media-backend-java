package com.chen.media.exception;

import com.chen.media.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理400 Bad Request
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleBadRequest(MissingServletRequestParameterException e) {
        e.printStackTrace();
//        Map<String, Object> response = new HashMap<>();
//        response.put("code", "400");
//        response.put("message", "参数错误");
//        response.put("data", null);
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return Result.fail(400, "参数错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return Result.fail(400, "参数错误");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return Result.fail(400, "参数错误");
    }


    // 处理404 Not Found
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Object> handleNotFound(NoResourceFoundException e) {
        e.printStackTrace();
        return Result.fail(404, "资源不存在");
    }


    @ExceptionHandler(Exception.class)
    public Result<Object> handle(Exception e) {
        e.printStackTrace();
        return Result.fail(500, "服务器内部错误");
    }

    @ExceptionHandler(CustomException.class)
    public Result<Object> handle(CustomException e) {
        String message = e.getMessage();
        Integer code = e.getCode();
        e.printStackTrace();
        return Result.fail(code, message);
    }

}
