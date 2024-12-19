<template>
  <el-container class="layout-container">
    <el-header>
      <div class="header-left">WMS仓库管理系统</div>
      <div class="header-right">
        <el-dropdown @command="handleCommand">
          <span class="user-info">
            {{ userStore.userInfo?.nickname || '未知用户' }}
            <el-icon><ArrowDown /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="profile">个人中心</el-dropdown-item>
              <el-dropdown-item command="changePassword">修改密码</el-dropdown-item>
              <el-dropdown-item divided command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>
    
    <el-container>
      <el-aside width="200px">
        <el-menu
          router
          :default-active="$route.path"
          class="menu-container"
        >
          <el-menu-item index="/dashboard">
            <el-icon><HomeFilled /></el-icon>
            <span>首页</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { ArrowDown, HomeFilled } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'

const userStore = useUserStore()
const router = useRouter()

onMounted(async () => {
  if (!userStore.userInfo) {
    await userStore.fetchUserInfo()
  }
})

const handleCommand = (command) => {
  switch (command) {
    case 'profile':
      router.push('/profile')
      break
    case 'changePassword':
      router.push('/profile/password')
      break
    case 'logout':
      userStore.logout()
      router.push('/login')
      break
  }
}
</script>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;
  
  .el-header {
    background-color: #409EFF;
    color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 20px;
    
    .header-left {
      font-size: 20px;
      font-weight: bold;
    }
    
    .header-right {
      .user-info {
        color: #fff;
        cursor: pointer;
        display: flex;
        align-items: center;
        gap: 4px;
        &:hover {
          opacity: 0.8;
        }
      }
    }
  }
  
  .el-aside {
    background-color: #fff;
    border-right: 1px solid #e6e6e6;
    
    .menu-container {
      height: 100%;
    }
  }
  
  .el-main {
    background-color: #f5f5f5;
    padding: 20px;
  }
}
</style> 