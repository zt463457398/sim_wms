import { useUserStore } from '@/stores/user'

export function checkPermission(permission) {
  const userStore = useUserStore()
  const userPermissions = userStore.userInfo?.permissions || []
  
  if (typeof permission === 'string') {
    return userPermissions.includes(permission)
  }
  
  if (Array.isArray(permission)) {
    return permission.some(p => userPermissions.includes(p))
  }
  
  return false
} 