package com.zhgw.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000; // 24小时
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    /**
     * 生成token
     */
    public String generateToken(Long userId, String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRE_TIME);
        
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }
    
    /**
     * 解析token
     */
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * 验证token是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static String getUserId(String token) {
        try {
            // 检查 token 是否为空
            if (token == null || token.isEmpty()) {
                throw new RuntimeException("Token不能为空");
            }
            
            // 解析 token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
                    
            // 获取并检查 userId
            Object userId = claims.get("userId");
            if (userId == null) {
                throw new RuntimeException("Token中未包含用户ID");
            }
            
            return String.valueOf(userId);
        } catch (Exception e) {
            throw new RuntimeException("Token解析失败: " + e.getMessage());
        }
    }
} 