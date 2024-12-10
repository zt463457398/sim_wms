package com.zhgw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhgw.entity.User;
import com.zhgw.mapper.UserMapper;
import com.zhgw.service.UserService;
import com.zhgw.util.PasswordEncoder;
import com.zhgw.dto.LoginDTO;
import com.zhgw.vo.LoginVO;
import com.zhgw.util.JwtUtil;
import com.zhgw.common.ApiException;
import com.zhgw.common.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }
    
    @Override
    public boolean createUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return save(user);
    }
    
    @Override
    public boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        // 获取用户
        User user = getById(userId);
        if (user == null) {
            throw new ApiException(ResultCode.USER_NOT_EXIST);
        }
        
        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new ApiException(ResultCode.OLD_PASSWORD_ERROR);
        }
        
        // 更新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        return updateById(user);
    }
    
    @Override
    public LoginVO login(LoginDTO loginDTO) {
        User user = getUserByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new ApiException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        boolean passwordMatches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new ApiException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        if (user.getStatus() != null && user.getStatus() == 0) {
            throw new ApiException(ResultCode.ACCOUNT_DISABLED);
        }
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(user, loginVO);
        loginVO.setUserId(user.getId());
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        loginVO.setToken(token);
        return loginVO;
    }
} 