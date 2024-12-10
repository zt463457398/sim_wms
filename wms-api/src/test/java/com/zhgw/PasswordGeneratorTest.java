package com.zhgw;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorTest {
    
    @Test
    public void generatePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123";
        String encodedPassword = encoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);
        
        // 添加验证测试
        boolean matches = encoder.matches("admin123", encodedPassword);
        System.out.println("Password matches: " + matches);
        
        // 验证数据库中的密码
        String dbPassword = "$2a$10$B6Im8d2Uf0PHjgQTDujctOqVvro92N2ywIz.XLBqyGzAtqTtYqi7S";
        boolean dbMatches = encoder.matches("admin123", dbPassword);
        System.out.println("DB password matches: " + dbMatches);
    }
} 