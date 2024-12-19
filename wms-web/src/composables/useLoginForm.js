import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { ElMessage } from 'element-plus'

export function useLoginForm() {
  const router = useRouter()
  const userStore = useUserStore()
  const loading = ref(false)
  const formRef = ref()

  const loginForm = reactive({
    username: '',
    password: ''
  })

  const rules = {
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
    ]
  }

  const handleLogin = async () => {
    if (!formRef.value) return

    try {
      loading.value = true
      await formRef.value.validate()
      
      await userStore.login({
        username: loginForm.username,
        password: loginForm.password
      })

      ElMessage.success('登录成功')
      
      // 获取重定向地址或默认跳转到首页
      const redirect = router.currentRoute.value.query.redirect || '/'
      router.push(redirect)
    } catch (error) {
      ElMessage.error(error.message || '登录失败')
    } finally {
      loading.value = false
    }
  }

  return {
    loginForm,
    loading,
    formRef,
    rules,
    handleLogin
  }
} 