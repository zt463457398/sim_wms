import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', {
  state: () => ({
    loading: false,
    loadingText: '',
    loadingCount: 0
  }),

  actions: {
    startLoading(text = '加载中...') {
      this.loadingCount++
      this.loading = true
      this.loadingText = text
    },

    stopLoading() {
      this.loadingCount--
      if (this.loadingCount <= 0) {
        this.loadingCount = 0
        this.loading = false
        this.loadingText = ''
      }
    }
  }
}) 