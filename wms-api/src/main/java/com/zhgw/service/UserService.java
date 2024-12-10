package com.zhgw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhgw.dto.LoginDTO;
import com.zhgw.entity.User;
import com.zhgw.vo.LoginVO;

public interface UserService extends IService<User> {
    User getUserByUsername(String username);
    
    /**
     * 创建用户（包含密码加密）
     */
    boolean createUser(User user);
    
    /**
     * 修改密码
     */
    boolean updatePassword(Long userId, String oldPassword, String newPassword);
    
    /**
     * 用户登录
     */
    LoginVO login(LoginDTO loginDTO);
} 