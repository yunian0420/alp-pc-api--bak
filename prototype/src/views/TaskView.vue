<template>
  <div class="page-card" v-if="task">
    <div class="section-title">任务查看</div>
    <p class="page-desc">总览任务、供应商以及每条主评 / 回复 / 叠楼 / 主评叠楼的评论状态、点赞状态和归属供应商。</p>
    <div class="view-grid">
      <div class="info-item">
        <div class="info-label">任务编号</div>
        <div class="info-value">{{ task.taskNo }}</div>
      </div>
      <div class="info-item">
        <div class="info-label">任务状态</div>
        <div class="info-value">{{ taskStatusLabel }}</div>
      </div>
      <div class="info-item">
        <div class="info-label">归属供应商</div>
        <div class="info-value">{{ task.supplierName || '未关联' }}</div>
      </div>
      <div class="info-item">
        <div class="info-label">更新时间</div>
        <div class="info-value">{{ task.updateTime }}</div>
      </div>
    </div>
    <div class="section-title">评论与点赞明细</div>
    <div class="comment-tree">
      <CommentPreview
        v-for="item in task.comments"
        :key="item.id"
        :node="item"
        :show-owner="true"
      />
    </div>
    <div class="footer-bar">
      <el-button type="primary" @click="goPublish">去发布 / 调整供应商</el-button>
      <el-button @click="goList">返回列表</el-button>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import CommentPreview from '@/components/CommentPreview.vue'
import { TASK_STATUS } from '@/constants'
import { useTaskStore } from '@/stores/taskStore'

export default {
  name: 'TaskView',
  components: {
    CommentPreview
  },
  setup() {
    const store = useTaskStore()
    const route = useRoute()
    const router = useRouter()
    const task = ref(null)
    const taskStatusLabel = computed(() => {
      if (!task.value) {
        return ''
      }
      return TASK_STATUS[task.value.status].label
    })

    const goList = () => router.push('/tasks')
    const goPublish = () => router.push(`/publish/${task.value.id}`)

    onMounted(() => {
      const current = store.getTask(route.params.id)
      if (!current) {
        ElMessage.error('任务不存在')
        router.push('/tasks')
        return
      }
      task.value = current
    })

    return {
      task,
      taskStatusLabel,
      goList,
      goPublish
    }
  }
}
</script>
