<template>
  <div class="page-card">
    <div class="section-title">控评任务列表</div>
    <p class="page-desc">先创建评论和点赞内容，再关联供应商发布，发布后可查看每条评论/点赞的状态与归属。</p>
    <div style="display: flex; justify-content: space-between; gap: 12px; margin-bottom: 16px;">
      <div style="display: flex; gap: 12px;">
        <el-input v-model="keyword" placeholder="搜索任务编号 / 主评内容" clearable style="width: 260px" />
        <el-select v-model="status" clearable placeholder="任务状态" style="width: 160px">
          <el-option
            v-for="(item, key) in taskStatusMap"
            :key="key"
            :label="item.label"
            :value="key"
          />
        </el-select>
        <el-select v-model="supplierId" clearable placeholder="归属供应商" style="width: 180px">
          <el-option
            v-for="item in store.suppliers"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </div>
      <el-button type="primary" @click="goCreate">创建评论点赞</el-button>
    </div>
    <el-table :data="pagedList" stripe>
      <el-table-column prop="taskNo" label="任务编号" width="180" />
      <el-table-column label="主评内容" min-width="280">
        <template #default="{ row }">
          {{ row.summary || '-' }}
        </template>
      </el-table-column>
      <el-table-column label="评论数" width="90" align="center">
        <template #default="{ row }">{{ row.commentCount }}</template>
      </el-table-column>
      <el-table-column label="点赞数" width="90" align="center">
        <template #default="{ row }">{{ row.likeCount }}</template>
      </el-table-column>
      <el-table-column label="归属供应商" width="140">
        <template #default="{ row }">{{ row.supplierName || '未关联' }}</template>
      </el-table-column>
      <el-table-column label="状态" width="110">
        <template #default="{ row }">
          <el-tag :type="taskStatusMap[row.status].type" size="small">
            {{ taskStatusMap[row.status].label }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="170" />
      <el-table-column label="操作" width="220" fixed="right">
        <template #default="{ row }">
          <el-button v-if="row.status === 'draft'" link type="primary" @click="goEdit(row.id)">
            编辑
          </el-button>
          <el-button link type="primary" @click="goPublish(row.id)">发布</el-button>
          <el-button link type="primary" @click="goView(row.id)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex; justify-content: flex-end; margin-top: 16px;">
      <el-pagination
        v-model:current-page="page"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="filteredList.length"
      />
    </div>
  </div>
</template>

<script>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { TASK_STATUS } from '@/constants'
import { useTaskStore } from '@/stores/taskStore'

export default {
  name: 'TaskList',
  setup() {
    const store = useTaskStore()
    const router = useRouter()
    const keyword = ref('')
    const status = ref('')
    const supplierId = ref('')
    const page = ref(1)
    const pageSize = 8

    const filteredList = computed(() => {
      return store.taskList.filter((item) => {
        const text = `${item.taskNo}${item.summary || ''}`
        const matchKeyword = !keyword.value || text.indexOf(keyword.value) > -1
        const matchStatus = !status.value || item.status === status.value
        const matchSupplier = !supplierId.value || item.supplierId === supplierId.value
        return matchKeyword && matchStatus && matchSupplier
      })
    })

    const pagedList = computed(() => {
      const start = (page.value - 1) * pageSize
      return filteredList.value.slice(start, start + pageSize)
    })

    const goCreate = () => router.push('/create')
    const goEdit = (id) => router.push(`/edit/${id}`)
    const goPublish = (id) => router.push(`/publish/${id}`)
    const goView = (id) => router.push(`/view/${id}`)

    return {
      store,
      keyword,
      status,
      supplierId,
      page,
      pageSize,
      filteredList,
      pagedList,
      taskStatusMap: TASK_STATUS,
      goCreate,
      goEdit,
      goPublish,
      goView
    }
  }
}
</script>
