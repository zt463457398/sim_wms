<template>
  <error-boundary>
    <router-view />
    <request-loading ref="loadingRef" />
  </error-boundary>
</template>

<script setup>
import ErrorBoundary from '@/components/ErrorBoundary.vue'
import RequestLoading from '@/components/RequestLoading.vue'
import { ref, provide } from 'vue'

const loadingRef = ref(null)

// 提供加载状态控制方法给子组件使用
provide('loading', {
  show: () => loadingRef.value?.show(),
  hide: () => loadingRef.value?.hide()
})
</script>

<style>
html, body {
  margin: 0;
  padding: 0;
  height: 100%;
}

#app {
  height: 100%;
}
</style>
