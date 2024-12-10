package com.zhgw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhgw.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    
    private String username;
    
    private String password;
    
    private String nickname;
    
    private String email;
    
    private String phone;
    
    private String avatar;
    
    private Integer status; // 0-禁用 1-正常
    
    private Integer gender; // 0-未知 1-男 2-女
    
    private String remark;
} 