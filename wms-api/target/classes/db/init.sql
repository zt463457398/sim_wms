-- 创建数据库
CREATE DATABASE IF NOT EXISTS sim_wms DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE sim_wms;

-- 创建用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    nickname VARCHAR(50) COMMENT '昵称',
    email VARCHAR(100) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    avatar VARCHAR(255) COMMENT '头像URL',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
    gender TINYINT DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
    remark VARCHAR(500) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(50) COMMENT '创建人',
    update_by VARCHAR(50) COMMENT '更新人',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    INDEX idx_username (username),
    INDEX idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 插入管理员账号（密码为：admin123，使用BCrypt加密）
INSERT INTO sys_user (username, password, nickname, status, gender, remark, create_by)
VALUES (
    'admin',
    '$2a$10$N.ZOn9G6/YLFixAOPMg/h.z7pCu6v2XyFDtC4q.jeeGM/qdhbqIQC',
    '系统管理员',
    1,
    1,
    '系统管理员账号',
    'system'
); 

-- 检查数据库中的密码
SELECT username, password FROM sys_user WHERE username = 'admin';