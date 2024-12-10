package com.zhgw.common;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final Integer code;
    
    public ApiException(String message) {
        super(message);
        this.code = 500;
    }
    
    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    
    public ApiException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }
} 