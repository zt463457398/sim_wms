import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '@/router/router'

const request = axios.create({
  baseURL: '/api',
  timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 200) {
      ElMessage.error(res.message || '系统错误')
      if (res.code === 401) {
        ElMessage.warning('登录已过期，请重新登录')
        localStorage.removeItem('token')
        router.push({
          name: 'Login',
          query: {
            redirect: router.currentRoute.value.fullPath
          }
        })
      }
      return Promise.reject(new Error(res.message || '系统错误'))
    }
    return res
  },
  error => {
    let message = '系统错误'
    if (error.response) {
      switch (error.response.status) {
        case 401:
          message = '未登录或登录已过期'
          localStorage.removeItem('token')
          router.push({
            name: 'Login',
            query: {
              redirect: router.currentRoute.value.fullPath
            }
          })
          break
        case 403:
          message = '没有权限'
          break
        case 404:
          message = '请求的资源不存在'
          break
        case 500:
          message = '服务器错误'
          break
        default:
          message = error.response.data.message || '系统错误'
      }
    }
    ElMessage.error(message)
    return Promise.reject(error)
  }
)

export default request 