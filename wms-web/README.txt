WMS前端项目说明文档
=================

项目概述
-------------
WMS（仓库管理系统）前端项目，基于Vue 3 + Element Plus构建的现代化Web应用。

技术栈
-------------
- 核心框架：Vue 3
- 状态管理：Pinia
- UI框架：Element Plus
- 路由：Vue Router
- HTTP客户端：Axios
- 构建工具：Vite

目录结构
-------------
wms-web/
├── src/
│   ├── api/            # API请求
│   ├── assets/         # 静态资源
│   ├── components/     # 公共组件
│   ├── composables/    # 组合式函数
│   ├── directives/     # 自定义指令
│   ├── layout/         # 布局组件
│   ├── router/         # 路由配置
│   ├── stores/         # 状态管理
│   ├── utils/          # 工具函数
│   └── views/          # 页面组件

主要功能
-------------
1. 用户认证
   - 登录页面
   - Token管理
   - 路由守卫

2. 个人中心
   - 查看个人信息
   - 修改个人信息
   - 修改密码

3. 全局功能
   - 请求加载状态
   - 错误处理
   - 权限控制

组件说明
-------------
1. 布局组件（Layout）
   - 顶部导航
   - 侧边菜单
   - 内容区域

2. 全局组件
   - ErrorBoundary：错误边界处理
   - RequestLoading：请求加载状态
   - GlobalLoading：全局加载

状态管理
-------------
1. 用户状态（UserStore）
   - 用户信息
   - Token管理
   - 登录状态

2. 应用状态（AppStore）
   - 加载状态
   - 全局设置

路由配置
-------------
1. 基础路由
   - /login：登录页
   - /：主页
   - /profile：个人中心
   - /profile/password：修改密码

2. 路由守卫
   - 登录状态检查
   - 权限验证
   - 页面标题设置

API请求
-------------
1. 请求配置
   - 基础URL配置
   - 超时设置
   - Token注入

2. 响应处理
   - 统一错误处理
   - 登录状态检查
   - 加载状态管理

开发指南
-------------
1. 环境准备
   - Node.js 16+
   - npm 或 yarn

2. 开发命令
   - 安装依赖：npm install
   - 开发服务：npm run dev
   - 构建：npm run build
   - 代码检查：npm run lint

3. 开发规范
   - 组件命名：多词组合
   - 样式规范：BEM命名
   - 代码格式：ESLint + Prettier

部署说明
-------------
1. 构建
   - npm run build
   - 生成dist目录

2. 部署
   - 配置Nginx
   - 设置baseURL
   - 配置跨域 

+ 学习指南
+ -------------
+ 1. 前置知识
+    - JavaScript ES6+ 基础
+    - Vue 3 基础概念
+    - TypeScript 基础（可选）
+    - npm 包管理工具使用
+ 
+ 2. 关键概念学习
+    - Vue 3 Composition API
+    - Pinia 状态管理
+    - Vue Router 路由管理
+    - Element Plus 组件使用
+ 
+ 3. 项目结构说明
+    - api/：API 接口封装，按模块划分
+    - components/：可复用组件
+    - composables/：组合式函数，提取公共逻辑
+    - stores/：Pinia 状态管理，分模块管理
+    - views/：页面组件，对应路由
+ 
+ 4. 开发流程示例
+    4.1 添加新页面
+    - 在 views/ 下创建页面组件
+    - 在 router/ 中添加路由配置
+    - 在 api/ 中添加相关接口
+    - 在 stores/ 中添加状态管理（如需要）
+ 
+    4.2 添加新功能
+    - 分析功能需求
+    - 设计组件结构
+    - 实现数据流转
+    - 添加错误处理
+    - 进行测试验证
+ 
+ 5. 最佳实践
+    - 组件设计原则
+      * 单一职责
+      * 可复用性
+      * Props 验证
+      * 事件命名规范
+ 
+    - 状态管理
+      * 合理使用 Pinia
+      * 避免重复状态
+      * 保持状态同步
+ 
+    - 性能优化
+      * 路由懒加载
+      * 组件按需导入
+      * 合理使用缓存
+ 
+ 6. 常见问题解决
+    - 跨域问题处理
+    - Token 失效处理
+    - 请求错误处理
+    - 权限控制实现
+ 
+ 7. 扩展开发
+    - 新增模块步骤
+    - 自定义组件开发
+    - 主题定制方法
+    - 插件集成指南

+ 快速开始
+ -------------
+ 1. 环境准备
+    ```bash
+    # 检查 Node.js 版本
+    node -v  # 需要 16+
+    
+    # 安装依赖
+    npm install
+    
+    # 启动开发服务器
+    npm run dev
+    ```
+ 
+ 2. 项目配置
+    - .env.development：开发环境配置
+    - .env.production：生产环境配置
+    - vite.config.js：构建配置
+ 
+ 3. 代码示例
+    - 组件开发示例
+    ```vue
+    <template>
+      <div class="example-component">
+        <el-form :model="form" :rules="rules">
+          <!-- 表单内容 -->
+        </el-form>
+      </div>
+    </template>
+    
+    <script setup>
+    // 组合式API示例
+    const form = reactive({})
+    const rules = {}
+    </script>
+    ```
+ 
+    - API调用示例
+    ```javascript
+    // 定义API
+    export function exampleApi(data) {
+      return request({
+        url: '/example',
+        method: 'post',
+        data
+      })
+    }
+    
+    // 使用API
+    const response = await exampleApi(data)
+    ```
+ 
+ 调试技巧
+ -------------
+ 1. Vue DevTools 使用
+ 2. 网络请求调试
+ 3. 状态管理调试
+ 4. 性能分析工具
+ 
+ 常见错误处理
+ -------------
+ 1. 环境配置问题
+ 2. 跨域问题
+ 3. 路由问题
+ 4. 状态管理问题