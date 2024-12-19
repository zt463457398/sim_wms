import { defineStore } from 'pinia'
import { getUserInfo, login } from '@/api/user'

export const useUserStore = defineStore('user', {
  state: () => ({
    userInfo: null,
    token: localStorage.getItem('token') || ''
  }),

  actions: {
    // 登录
    async login(loginData) {
      const res = await login(loginData)
      this.token = res.data.token
      localStorage.setItem('token', res.data.token)
      await this.fetchUserInfo()
      return res
    },

    // 获取用户信息
    async fetchUserInfo() {
      try {
        const res = await getUserInfo()
        this.userInfo = res.data
      } catch (error) {
        console.error('获取用户信息失败:', error)
      }
    },

    // 登出
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
    },

    // 清除用户信息
    clearUserInfo() {
      this.userInfo = null
      this.token = ''
      localStorage.removeItem('token')
    }
  }
}) 