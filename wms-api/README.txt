WMS后端项目说明文档
=================

项目概述
-------------
WMS（仓库管理系统）后端服务，基于Spring Boot + MyBatis-Plus构建，提供RESTful API接口。

技术栈
-------------
- 核心框架：Spring Boot 3.x
- ORM框架：MyBatis-Plus 3.5.3
- 数据库：MySQL 8.0
- 认证方案：JWT
- 项目管理：Maven

目录结构
-------------
wms-api/
├── src/main/java/com/zhgw/
│   ├── config/          # 配置类
│   ├── controller/      # 控制器
│   ├── service/         # 服务层
│   ├── entity/          # 实体类
│   ├── mapper/          # MyBatis接口
│   ├── util/            # 工具类
│   ├── vo/              # 视图对象
│   ├── dto/             # 数据传输对象
│   └── common/          # 公共类

主要功能
-------------
1. 用户认证
   - 登录接口：POST /auth/login
   - JWT token生成和验证
   - 密码加密存储

2. 用户管理
   - 获取用户信息：GET /user/info
   - 更新用户信息：PUT /user/update
   - 修改密码：POST /user/profile/password

3. 个人中心
   - 获取个人信息：GET /user/profile
   - 更新个人信息：PUT /user/profile

配置说明
-------------
1. 数据库配置（application.yml）
   - 数据库连接信息
   - MyBatis-Plus配置
   - 跨域配置

2. JWT配置
   - token有效期：24小时
   - 密钥生成：自动生成的HS256密钥

API接口说明
-------------
1. 认证接口
   POST /auth/login
   请求体：
   {
     "username": "用户名",
     "password": "密码"
   }
   响应：
   {
     "code": 200,
     "data": {
       "token": "jwt_token",
       "userId": "用户ID",
       "username": "用户名"
     }
   }

2. 用户信息接口
   GET /user/info
   请求头：
   Authorization: Bearer <token>
   响应：
   {
     "code": 200,
     "data": {
       "id": "用户ID",
       "username": "用户名",
       "nickname": "昵称",
       "email": "邮箱",
       "phone": "电话"
     }
   }

安全说明
-------------
1. 密码安全
   - 使用BCrypt加密存储
   - 密码不可逆加密

2. 接口安全
   - JWT token认证
   - 请求头验证
   - 跨域保护

部署说明
-------------
1. 环境要求
   - JDK 17+
   - MySQL 8.0+
   - Maven 3.6+

2. 部署步骤
   - 创建数据库：sim_wms
   - 执行SQL脚本：db/init.sql
   - 配置数据库连接
   - 打包：mvn clean package
   - 运行：java -jar wms-api.jar

开发指南
-------------
1. 添加新功能
   - 创建实体类
   - 创建Mapper接口
   - 实现Service层
   - 添加Controller接口
   - 编写单元测试

2. 代码规范
   - 遵循阿里巴巴Java开发手册
   - 使用统一的返回格式
   - 添加适当的注释

学习指南
-------------
1. 前置知识
   - Java 基础
   - Spring Boot 基础
   - MySQL 数据库
   - RESTful API 设计

2. 核心概念
   - Spring Boot 自动配置
   - MyBatis-Plus CRUD
   - JWT 认证流程
   - 统一响应处理

3. 项目结构详解
   - common/：公共类
     * Result：统一响应封装
     * BaseEntity：实体基类
     * ApiException：自定义异常

   - config/：配置类
     * WebMvcConfig：Web配置
     * MybatisPlusConfig：ORM配置
     * GlobalExceptionHandler：统一异常处理

   - controller/：控制器
     * 请求参数验证
     * 响应封装
     * 异常处理

   - service/：业务逻辑
     * 接口定义
     * 实现类
     * 事务管理

4. 开发流程示例
   - 添加新功能模块
   - 创建数据库表
   - 生成实体类
   - 编写 Mapper 接口
   - 实现 Service 层
   - 创建 Controller
   - 编写单元测试

5. 安全开发���南
   - 密码加密
   - Token 验证
   - 权限控制
   - SQL 注入防护
   - XSS 防护

6. 数据库开发
   - 表结构设计
   - 索引优化
   - 查询优化
   - 事务管理

7. 常见问题解决
   - 跨域配置
   - 文件上传
   - 日期处理
   - 分页查询
   - 多数据源

8. 测试指南
   - 单元测试
   - 接口测试
   - 性能测试
   - 安全测试

9. 部署运维
   - 环境配置
   - 打包部署
   - 日志管理
   - 性能监控
   - 问题排查

快速开始
-------------
1. 环境准备
   ```bash
   # 检查Java版本
   java -version  # 需要JDK 17+
   
   # 检查Maven版本
   mvn -v  # 需要3.6+
   
   # 构建项目
   mvn clean install
   ```

2. 配置文件说明
   - application.yml：基础配置
   - application-dev.yml：开发环境配置
   - application-prod.yml：生产环境配置

3. 代码示例
   - Controller示例
   ```java
   @RestController
   @RequestMapping("/example")
   public class ExampleController {
       @GetMapping("/{id}")
       public Result<ExampleVO> getById(@PathVariable Long id) {
           // 实现逻辑
       }
   }
   ```

   - Service示例
   ```java
   @Service
   public class ExampleServiceImpl implements ExampleService {
       @Transactional
       public void example() {
           // 事务处理示例
       }
   }
   ```

4. 调试技巧
   - IDE调试技巧
   - 日志调试
   - 数据库调试
   - 接口测试

5. 性能优化指南
   - SQL优化
   - JVM调优
   - 缓存使用
   - 并发处理

6. 版本控制
   - Git分支管理
   - 代码提交规范
   - 版本发布流程
   - 代码审查指南