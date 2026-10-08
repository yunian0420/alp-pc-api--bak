<template>
  <div class="app-layout">
    <aside class="sidebar">
      <div class="logo">
        <div class="logo-mark">评</div>
        <div class="logo-title">控评中心</div>
      </div>
      <router-link class="nav-item" :class="{ active: isTaskNav }" to="/tasks">
        控评任务列表
      </router-link>
      <router-link class="nav-item" :class="{ active: isCreateNav }" to="/create">
        创建评论点赞
      </router-link>
    </aside>
    <div class="main-wrap">
      <header class="topbar">
        <div class="page-title">{{ pageTitle }}</div>
        <div style="color: #86909c; font-size: 13px;">第一版原型 · 本地演示数据</div>
      </header>
      <main class="content">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

export default {
  name: 'AppLayout',
  setup() {
    const route = useRoute()
    const pageTitle = computed(() => route.meta.title || '控评中心')
    const isTaskNav = computed(() => {
      return ['TaskList', 'TaskPublish', 'TaskView'].includes(route.name)
    })
    const isCreateNav = computed(() => {
      return ['CommentCreate', 'CommentEdit'].includes(route.name)
    })
    return {
      pageTitle,
      isTaskNav,
      isCreateNav
    }
  }
}
</script>
