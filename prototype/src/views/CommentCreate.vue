<template>
  <div class="page-card">
    <div class="section-title">{{ isEdit ? '编辑评论点赞' : '创建评论点赞' }}</div>
    <p class="page-desc">
      可新建主评，主评填写内容、图片和点赞数；主评下可新增回复；回复下可新增叠楼和主评叠楼。
    </p>
    <div v-if="comments.length">
      <CommentEditor
        v-for="(item, index) in comments"
        :key="item.id"
        :node="item"
        @remove="removeMain(index)"
      />
    </div>
    <div v-else class="empty-block">还没有主评，点击下方按钮开始创建</div>
    <el-button class="add-main-btn" dashed @click="addMain">+ 新增主评</el-button>
    <div class="footer-bar">
      <el-button type="primary" @click="handleSave(false)">保存草稿</el-button>
      <el-button type="primary" @click="handleSave(true)">保存并去发布</el-button>
      <el-button @click="goList">取消</el-button>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus'
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import CommentEditor from '@/components/CommentEditor.vue'
import { useTaskStore } from '@/stores/taskStore'

export default {
  name: 'CommentCreate',
  components: {
    CommentEditor
  },
  setup() {
    const store = useTaskStore()
    const route = useRoute()
    const router = useRouter()
    const comments = ref([])
    const isEdit = ref(false)

    const addMain = () => {
      comments.value.push(store.createEmptyMain())
    }

    const removeMain = (index) => {
      comments.value.splice(index, 1)
    }

    const validateTree = (list) => {
      for (let i = 0; i < list.length; i++) {
        const item = list[i]
        if (!item.content || !item.content.trim()) {
          return false
        }
        if (item.children && item.children.length && !validateTree(item.children)) {
          return false
        }
      }
      return true
    }

    const handleSave = (goPublish) => {
      if (!comments.value.length) {
        ElMessage.warning('请至少新增一条主评')
        return
      }
      if (!validateTree(comments.value)) {
        ElMessage.warning('请完善每条评论的内容')
        return
      }
      const id = store.saveDraft({
        id: route.params.id,
        comments: comments.value
      })
      ElMessage.success('保存成功')
      if (goPublish) {
        router.push(`/publish/${id}`)
        return
      }
      router.push('/tasks')
    }

    const goList = () => router.push('/tasks')

    onMounted(() => {
      if (route.params.id) {
        const task = store.getTask(route.params.id)
        if (!task) {
          ElMessage.error('任务不存在')
          router.push('/tasks')
          return
        }
        isEdit.value = true
        comments.value = JSON.parse(JSON.stringify(task.comments))
        return
      }
      addMain()
    })

    return {
      comments,
      isEdit,
      addMain,
      removeMain,
      handleSave,
      goList
    }
  }
}
</script>
