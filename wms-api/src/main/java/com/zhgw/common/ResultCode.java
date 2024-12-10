package com.zhgw.common;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    UNAUTHORIZED(401, "未登录或token已过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数检验失败"),
    FAILED(500, "操作失败"),
    
    // 用户相关错误
    USER_NOT_EXIST(1001, "用户不存在"),
    USERNAME_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    ACCOUNT_DISABLED(1003, "账号已被禁用"),
    OLD_PASSWORD_ERROR(1004, "原密码错误");
    
    private final Integer code;
    private final String message;
    
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
} 