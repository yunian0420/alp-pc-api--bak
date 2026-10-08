<template>
  <div class="page-card" v-if="task">
    <div class="section-title">任务发布</div>
    <p class="page-desc">将评论点赞内容关联到一个供应商后生成任务记录，发布后可在查看页跟踪状态和归属。</p>
    <div class="section-title">控评内容</div>
    <div class="comment-tree">
      <CommentPreview
        v-for="item in task.comments"
        :key="item.id"
        :node="item"
      />
    </div>
    <div class="section-title" style="margin-top: 24px;">选择供应商</div>
    <el-radio-group v-model="supplierId">
      <el-radio
        v-for="item in store.suppliers"
        :key="item.id"
        :value="item.id"
        style="margin-right: 24px;"
      >
        {{ item.name }}
      </el-radio>
    </el-radio-group>
    <div class="footer-bar">
      <el-button type="primary" @click="handleSubmit">提交发布</el-button>
      <el-button @click="goBack">取消</el-button>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import CommentPreview from '@/components/CommentPreview.vue'
import { useTaskStore } from '@/stores/taskStore'

export default {
  name: 'TaskPublish',
  components: {
    CommentPreview
  },
  setup() {
    const store = useTaskStore()
    const route = useRoute()
    const router = useRouter()
    const task = ref(null)
    const supplierId = ref('')

    const handleSubmit = () => {
      if (!supplierId.value) {
        ElMessage.warning('请选择供应商')
        return
      }
      store.publishTask(task.value.id, supplierId.value)
      ElMessage.success('已关联供应商并生成任务记录')
      router.push(`/view/${task.value.id}`)
    }

    const goBack = () => router.push('/tasks')

    onMounted(() => {
      const current = store.getTask(route.params.id)
      if (!current) {
        ElMessage.error('任务不存在')
        router.push('/tasks')
        return
      }
      task.value = current
      supplierId.value = current.supplierId || ''
    })

    return {
      store,
      task,
      supplierId,
      handleSubmit,
      goBack
    }
  }
}
</script>
