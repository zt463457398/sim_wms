package com.zhgw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhgw.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 