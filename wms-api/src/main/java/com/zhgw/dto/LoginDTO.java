package com.zhgw.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    
    @Override
    public String toString() {
        return "LoginDTO{" +
            "username='" + username + '\'' +
            ", password='[PROTECTED]'" +
            '}';
    }
} 