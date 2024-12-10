import { defineStore } from 'pinia'
import request from '@/utils/request'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem('token') || '',
    userInfo: null
  }),
  actions: {
    async login(data) {
      try {
        const res = await request.post('/auth/login', data)
        this.token = res.data.token
        this.userInfo = res.data
        localStorage.setItem('token', res.data.token)
        return res
      } catch (error) {
        return Promise.reject(error)
      }
    },
    logout() {
      this.token = ''
      this.userInfo = null
      localStorage.removeItem('token')
    }
  }
}) 