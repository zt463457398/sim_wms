import { ref, reactive } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/stores/user'

export function useLoginForm() {
  const router = useRouter()
  const route = useRoute()
  const userStore = useUserStore()
  const loading = ref(false)
  const formRef = ref()

  const loginForm = reactive({
    username: '',
    password: ''
  })

  const rules = {
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
      { min: 3, max: 20, message: '用户名长度应在3-20个字符之间', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 6, max: 20, message: '密码长度应在6-20个字符之间', trigger: 'blur' }
    ]
  }

  const handleLogin = async () => {
    if (!formRef.value) return
    await formRef.value.validate(async (valid) => {
      if (valid) {
        loading.value = true
        try {
          console.log('Login payload:', {
            username: loginForm.username,
            password: loginForm.password
          })
          await userStore.login(loginForm)
          ElMessage.success('登录成功')
          const redirect = route.query.redirect || '/'
          router.push(redirect)
        } catch (error) {
          ElMessage.error(error.message || '登录失败')
        } finally {
          loading.value = false
        }
      }
    })
  }

  return {
    loading,
    formRef,
    loginForm,
    rules,
    handleLogin
  }
} 