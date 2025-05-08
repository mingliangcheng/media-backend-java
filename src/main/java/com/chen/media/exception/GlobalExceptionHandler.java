package com.chen.media.exception;

import com.chen.media.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Result handle(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

}
