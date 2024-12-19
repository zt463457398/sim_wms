package com.zhgw.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {
    
    public static String getCurrentUserId() {
        String token = getTokenFromRequest();
        if (token == null) {
            throw new RuntimeException("未登录");
        }
        return JwtUtil.getUserId(token);
    }
    
    private static String getTokenFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("获取请求上下文失败");
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        
        // 检查并处理 Bearer token
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        
        throw new RuntimeException("无效的token格式");
    }
} 