<template>
  <div v-if="error" class="error-container">
    <el-result
      icon="error"
      :title="error.message || '系统错误'"
      :sub-title="error.details || '请稍后重试'"
    >
      <template #extra>
        <el-button type="primary" @click="handleRetry">重试</el-button>
        <el-button @click="handleBack">返回</el-button>
      </template>
    </el-result>
  </div>
  <slot v-else></slot>
</template>

<script setup>
import { ref, onErrorCaptured } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const error = ref(null)

onErrorCaptured((err) => {
  error.value = err
  return false
})

const handleRetry = () => {
  error.value = null
  window.location.reload()
}

const handleBack = () => {
  error.value = null
  router.back()
}
</script>

<style scoped>
.error-container {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style> 