<template>
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <h2 class="login-title">WMS系统登录</h2>
      </template>
      
      <el-form
        ref="formRef"
        :model="loginForm"
        :rules="rules"
        label-width="0"
        @keyup.enter="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="用户名"
            prefix-icon="User"
          />
        </el-form-item>
        
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <el-form-item>
          <el-button
            type="primary"
            :loading="loading"
            class="login-button"
            @click="handleLogin"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { useLoginForm } from '@/composables/useLoginForm'

const {
  loginForm,
  loading,
  formRef,
  rules,
  handleLogin
} = useLoginForm()
</script>

<style lang="scss" scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
  
  .login-card {
    width: 100%;
    max-width: 400px;
    
    .login-title {
      text-align: center;
      margin: 0;
      color: #409EFF;
    }
    
    .login-button {
      width: 100%;
    }
  }
}
</style> 