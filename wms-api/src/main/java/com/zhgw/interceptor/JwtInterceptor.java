package com.zhgw.interceptor;

import com.zhgw.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.zhgw.common.ApiException;
import com.zhgw.common.ResultCode;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        
        String token = bearerToken.substring(7);  // 去掉"Bearer "前缀
        if (!jwtUtil.validateToken(token)) {
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        
        Claims claims = jwtUtil.parseToken(token);
        request.setAttribute("userId", claims.get("userId"));
        request.setAttribute("username", claims.getSubject());
        
        return true;
    }
} 