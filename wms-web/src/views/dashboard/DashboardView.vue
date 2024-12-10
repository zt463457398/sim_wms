<template>
  <el-card class="welcome-card">
    <template #header>
      <div class="card-header">
        <span>欢迎使用</span>
      </div>
    </template>
    <div class="welcome-content">
      <h1>欢迎登录WMS仓库管理系统</h1>
      <p>当前时间：{{ currentTime }}</p>
      <p>欢迎您，{{ userStore.userInfo?.nickname || '未知用户' }}</p>
    </div>
  </el-card>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const currentTime = ref(new Date().toLocaleString())
let timer

onMounted(() => {
  timer = window.setInterval(() => {
    currentTime.value = new Date().toLocaleString()
  }, 1000)
})

onUnmounted(() => {
  clearInterval(timer)
})
</script>

<style lang="scss" scoped>
.welcome-card {
  .card-header {
    font-weight: bold;
  }
  
  .welcome-content {
    text-align: center;
    padding: 40px 0;
    
    h1 {
      color: #409EFF;
      margin-bottom: 20px;
    }
    
    p {
      color: #666;
      margin: 10px 0;
    }
  }
}
</style> 