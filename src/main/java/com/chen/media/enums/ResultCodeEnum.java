package com.chen.media.enums;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    // 数据操作错误定义
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    PRAM_NOT_MATCH(400, "参数不正确"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "未登录或token过期，请登录！"),
    FORBIDDEN(403, "没有相关权限"),
    NOT_FOUND(404, "没有找到相关数据"),
    ERROR(500, "系统异常"),
    ;


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
