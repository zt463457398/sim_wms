package com.zhgw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhgw.common.Result;
import com.zhgw.entity.User;
import com.zhgw.service.UserService;
import com.zhgw.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/page")
    public Result<Page<User>> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String phone) {
        
        Page<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(username != null, User::getUsername, username)
                .like(phone != null, User::getPhone, phone)
                .orderByDesc(User::getCreateTime);
        
        userService.page(page, wrapper);
        return Result.success(page);
    }
    
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }
    
    @PostMapping
    public Result<Boolean> save(@RequestBody User user) {
        return Result.success(userService.createUser(user));
    }
    
    @PostMapping("/updatePassword")
    public Result<Boolean> updatePassword(
            @RequestParam Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        return Result.success(userService.updatePassword(userId, oldPassword, newPassword));
    }
    
    @PutMapping
    public Result<Boolean> update(@RequestBody User user) {
        return Result.success(userService.updateById(user));
    }
    
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable Long id) {
        return Result.success(userService.removeById(id));
    }
    
    @GetMapping("/info")
    public Result<User> getCurrentUserInfo() {
        Long userId = Long.valueOf(SecurityUtils.getCurrentUserId());
        User user = userService.getUserInfo(userId);
        if (user != null) {
            user.setPassword(null);
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }
    
    @PutMapping("/update")
    public Result<?> updateUserInfo(@RequestBody User user) {
        Long currentUserId = Long.valueOf(SecurityUtils.getCurrentUserId());
        user.setId(currentUserId);
        
        User updateUser = new User();
        updateUser.setId(currentUserId);
        updateUser.setNickname(user.getNickname());
        updateUser.setPhone(user.getPhone());
        updateUser.setEmail(user.getEmail());
        
        boolean success = userService.updateProfile(updateUser);
        return success ? Result.success() : Result.error("更新失败");
    }
    
    /**
     * 获取当前用户个人信息
     */
    @GetMapping("/profile")
    public Result<User> getCurrentUserProfile() {
        Long userId = Long.valueOf(SecurityUtils.getCurrentUserId());
        User user = userService.getUserInfo(userId);
        if (user != null) {
            // 出于安全考虑，清除敏感信息
            user.setPassword(null);
            return Result.success(user);
        }
        return Result.error("获取用户信息失败");
    }
    
    /**
     * 更新当前用户个人信息
     */
    @PutMapping("/profile")
    public Result<?> updateCurrentUserProfile(@RequestBody User user) {
        Long currentUserId = Long.valueOf(SecurityUtils.getCurrentUserId());
        user.setId(currentUserId);
        
        // 只允许更新特定字段
        User updateUser = new User();
        updateUser.setId(currentUserId);
        updateUser.setNickname(user.getNickname());
        updateUser.setPhone(user.getPhone());
        updateUser.setEmail(user.getEmail());
        updateUser.setGender(user.getGender());
        updateUser.setAvatar(user.getAvatar());
        
        boolean success = userService.updateProfile(updateUser);
        return success ? Result.success() : Result.error("更新失败");
    }
    
    /**
     * 修改密码
     */
    @PostMapping("/profile/password")
    public Result<?> updatePassword(@RequestParam String oldPassword, 
                                  @RequestParam String newPassword) {
        Long userId = Long.valueOf(SecurityUtils.getCurrentUserId());
        boolean success = userService.updatePassword(userId, oldPassword, newPassword);
        return success ? Result.success() : Result.error("修改密码失败");
    }
} 