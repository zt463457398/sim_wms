<template>
  <div v-if="appStore.loading" class="global-loading">
    <el-loading
      :fullscreen="true"
      :text="appStore.loadingText"
      background="rgba(255, 255, 255, 0.8)"
    />
  </div>
</template>

<script setup>
import { useAppStore } from '@/stores/app'

const appStore = useAppStore()
</script> 