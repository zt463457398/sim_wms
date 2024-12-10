package com.zhgw.vo;

import lombok.Data;

@Data
public class LoginVO {
    private Long userId;
    private String username;
    private String nickname;
    private String avatar;
    private String token;
} 