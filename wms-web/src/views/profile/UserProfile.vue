<template>
  <div class="user-profile">
    <el-card class="profile-card">
      <template #header>
        <div class="card-header">
          <span>个人信息</span>
          <el-button type="primary" @click="handleEdit">编辑</el-button>
        </div>
      </template>
      
      <el-form label-width="100px" v-if="!isEditing">
        <el-form-item label="用户名">
          <span>{{ userInfo.username }}</span>
        </el-form-item>
        <el-form-item label="昵称">
          <span>{{ userInfo.nickname }}</span>
        </el-form-item>
        <el-form-item label="电话">
          <span>{{ userInfo.phone }}</span>
        </el-form-item>
        <el-form-item label="邮箱">
          <span>{{ userInfo.email }}</span>
        </el-form-item>
      </el-form>

      <el-form
        v-else
        :model="editForm"
        :rules="rules"
        ref="formRef"
        label-width="100px"
      >
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editForm.nickname" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="editForm.phone" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSave">保存</el-button>
          <el-button @click="isEditing = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/stores/user'
import { updateUserProfile } from '@/api/user'

const userInfo = ref({})
const userStore = useUserStore()
const isEditing = ref(false)
const formRef = ref()

const editForm = reactive({
  nickname: '',
  phone: '',
  email: ''
})

const rules = {
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  phone: [{ pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }],
  email: [{ type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }]
}

const fetchUserInfo = async () => {
  try {
    await userStore.fetchUserInfo()
    userInfo.value = userStore.userInfo
  } catch (error) {
    console.error('获取用户信息失败:', error)
  }
}

const handleEdit = () => {
  isEditing.value = true
  Object.assign(editForm, userInfo.value)
}

const handleSave = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        await updateUserProfile(editForm)
        ElMessage.success('更新成功')
        isEditing.value = false
        await fetchUserInfo()
      } catch (error) {
        ElMessage.error('更新失败')
      }
    }
  })
}

onMounted(() => {
  fetchUserInfo()
})
</script>

<style scoped>
.profile-container {
  padding: 20px;
}

.profile-card {
  max-width: 800px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style> 