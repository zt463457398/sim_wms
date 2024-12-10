<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="title">WMS系统登录</h2>
      <el-form ref="formRef" :model="loginForm" :rules="rules" @keyup.enter="handleLogin">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="用户名">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" type="password" placeholder="密码" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="handleLogin" class="login-button">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { useLoginForm } from '@/composables/useLoginForm'

const { loading, formRef, loginForm, rules, handleLogin } = useLoginForm()
</script>

<style lang="scss" scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;

  .login-card {
    width: 400px;
    
    .title {
      text-align: center;
      margin-bottom: 30px;
    }

    .login-button {
      width: 100%;
    }
  }
}
</style> 