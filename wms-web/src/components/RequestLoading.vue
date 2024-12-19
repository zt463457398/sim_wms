<template>
  <div v-if="loading" class="request-loading">
    <el-loading
      :fullscreen="true"
      text="加载中..."
      background="rgba(255, 255, 255, 0.8)"
    />
  </div>
</template>

<script setup>
import { ref, defineExpose } from 'vue'

const loading = ref(false)

// 导出方法供外部使用
defineExpose({
  show: () => loading.value = true,
  hide: () => loading.value = false
})
</script> 