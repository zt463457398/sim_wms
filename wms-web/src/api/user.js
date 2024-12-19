import request from '@/utils/request'

// 用户登录
export function login(data) {
  return request({
    url: '/auth/login',
    method: 'post',
    data
  })
}

// 获取当前用户信息
export function getUserInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

// 更新用户信息
export function updateUserInfo(data) {
  return request({
    url: '/user/update',
    method: 'put',
    data
  })
}

// 获取个人信息
export function getUserProfile() {
  return request({
    url: '/user/profile',
    method: 'get'
  })
}

// 更新个人信息
export function updateUserProfile(data) {
  return request({
    url: '/user/profile',
    method: 'put',
    data
  })
}

// 修改密码
export function updatePassword(data) {
  return request({
    url: '/user/profile/password',
    method: 'post',
    params: {
      oldPassword: data.oldPassword,
      newPassword: data.newPassword
    }
  })
} 