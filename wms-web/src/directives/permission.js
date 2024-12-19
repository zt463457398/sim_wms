import { checkPermission } from '@/utils/permission'

export const permission = {
  mounted(el, binding) {
    const { value } = binding
    if (value && !checkPermission(value)) {
      el.parentNode?.removeChild(el)
    }
  }
}

export function setupPermissionDirective(app) {
  app.directive('permission', permission)
} 