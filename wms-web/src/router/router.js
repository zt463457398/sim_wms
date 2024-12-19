import { createRouter, createWebHistory } from 'vue-router'
import { setupRouteGuards } from './guards'
import Layout from '@/layout/MainLayout.vue'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/login/LoginView.vue'),
    meta: {
      title: '登录',
      requiresAuth: false
    }
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/DashboardView.vue'),
        meta: { title: '首页', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/profile',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Profile',
        component: () => import('@/views/profile/UserProfile.vue'),
        meta: { 
          title: '个人中心', 
          icon: 'user',
          requiresAuth: true 
        }
      },
      {
        path: 'password',
        name: 'ChangePassword',
        component: () => import('@/views/profile/ChangePassword.vue'),
        meta: { 
          title: '修改密码',
          requiresAuth: true 
        }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 设置路由守卫
setupRouteGuards(router)

export default router 