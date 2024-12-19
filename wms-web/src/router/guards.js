import { useUserStore } from '@/stores/user'
import { ElMessage } from 'element-plus'

export function setupRouteGuards(router) {
  router.beforeEach(async (to, from, next) => {
    const userStore = useUserStore()
    const token = localStorage.getItem('token')
    
    // 设置页面标题
    document.title = to.meta.title ? `${to.meta.title} - WMS系统` : 'WMS系统'
    
    // 不需要登录的页面直接放行
    if (to.meta.requiresAuth === false) {
      if (token && to.path === '/login') {
        next('/')
      } else {
        next()
      }
      return
    }
    
    // 需要登录但没有token
    if (!token) {
      ElMessage.warning('请先登录')
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
      return
    }
    
    // 有token但没有用户信息，尝试获取用户信息
    if (token && !userStore.userInfo) {
      try {
        await userStore.fetchUserInfo()
      } catch (error) {
        userStore.clearUserInfo()
        next({
          path: '/login',
          query: { redirect: to.fullPath }
        })
        return
      }
    }
    
    next()
  })
} 