WMS-API 项目文档
===============

项目简介
-------
WMS-API是一个基于Spring Boot 3.4.0的仓库管理系统后端服务。

技术栈
------
- Spring Boot 3.4.0
- MyBatis-Plus 3.5.5
- MySQL
- JWT (JSON Web Token)
- Spring Security Crypto
- Lombok
- Maven

主要功能
-------
1. 用户管理
   - 用户注册
   - 用户登录
   - 密码加密存储（BCrypt）
   - 修改密码
   - 用户信息管理（增删改查）
   - 分页查询

2. 安全认证
   - JWT Token认证
   - 全局异常处理
   - 接口访问控制
   - 跨域支持

项目结构
-------
src/main/java/com/zhgw/
├── WmsApiApplication.java              // 应用程序入口
├── common/                            // 公共类
│   ├── ApiException.java             // 自定义异常
│   ├── BaseEntity.java              // 实体基类
│   ├── Result.java                  // 统一响应结果
│   └── ResultCode.java              // 响应状态码
├── config/                           // 配置类
│   ├── GlobalExceptionHandler.java  // 全局异常处理
│   ├── MybatisPlusConfig.java      // MyBatis-Plus配置
│   └── WebMvcConfig.java           // Web配置
├── controller/                       // 控制器层
│   ├── AuthController.java         // 认证控制器
│   └── UserController.java         // 用户控制器
├── service/                         // 服务层
│   ├── UserService.java           // 用户服务接口
│   └── impl/
│       └── UserServiceImpl.java   // 用户服务实现
├── mapper/                          // 数据访问层
│   └── UserMapper.java            // 用户Mapper
├── entity/                         // 实体类
│   └── User.java                  // 用户实体
├── dto/                           // 数据传输对象
│   └── LoginDTO.java             // 登录DTO
├── vo/                           // 视图对象
│   └── LoginVO.java             // 登录VO
├── util/                         // 工具类
│   ├── JwtUtil.java            // JWT工具类
│   └── PasswordEncoder.java    // 密码加密工具
└── interceptor/                  // 拦截器
    └── JwtInterceptor.java     // JWT认证拦截器

API接口
-------
1. 认证接口
   POST /auth/login - 用户登录

2. 用户接口
   GET    /user/page - 分页查询用户列表
   GET    /user/{id} - 获取用户详情
   POST   /user - 创建用户
   PUT    /user - 更新用户
   DELETE /user/{id} - 删除用户
   POST   /user/updatePassword - 修改密码

配置文件
-------
application.yml 包含以下主要配置：
- 服务器端口
- 数据库连接
- MyBatis-Plus配置
- 日志配置

安全特性
-------
1. 密码加密：使用BCrypt算法加密存储
2. Token认证：基于JWT的接口认证
3. 全局异常处理：统一的异常处理和响应
4. 跨域支持：支持跨域请求
5. 接口保护：除登录接口外，所有接口都需要Token认证

数据库表结构
-----------
sys_user表：
- id: 主键
- username: 用户名
- password: 密码（BCrypt加密）
- nickname: 昵称
- email: 邮箱
- phone: 手机号
- avatar: 头像
- status: 状态（0-禁用 1-正常）
- gender: 性别（0-未知 1-男 2-女）
- remark: 备注
- create_time: 创建时间
- update_time: 更新时间
- create_by: 创建人
- update_by: 更新人
- deleted: 逻辑删除标记 