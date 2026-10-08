import { defineStore } from 'pinia'
import { DEMO_TASKS, SUPPLIERS } from '@/constants'
import { formatTime, generateId, generateTaskNo } from '@/utils/id'

const STORAGE_KEY = 'kp-comment-tasks-v1'

function createEmptyComment(type, parentId) {
  return {
    id: generateId(type),
    type,
    parentId: parentId || null,
    content: '',
    images: [],
    likeCount: 0,
    status: 'unpublished',
    supplierId: '',
    supplierName: '',
    likeStatus: 'unpublished',
    likeSupplierId: '',
    likeSupplierName: '',
    children: []
  }
}

function cloneComments(comments) {
  return JSON.parse(JSON.stringify(comments || []))
}

function flattenComments(comments, result) {
  const list = result || []
  ;(comments || []).forEach((item) => {
    list.push(item)
    if (item.children && item.children.length) {
      flattenComments(item.children, list)
    }
  })
  return list
}

function loadTasks() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY)
    if (raw) {
      const parsed = JSON.parse(raw)
      if (Array.isArray(parsed) && parsed.length) {
        return parsed
      }
    }
  } catch (error) {
    console.warn('load tasks failed', error)
  }
  return cloneComments(DEMO_TASKS)
}

function countComments(comments) {
  return flattenComments(comments).length
}

function countLikes(comments) {
  return flattenComments(comments).reduce((sum, item) => sum + Number(item.likeCount || 0), 0)
}

function firstMainContent(comments) {
  const main = (comments || []).find((item) => item.type === 'main')
  return main ? main.content : ''
}

export const useTaskStore = defineStore('task', {
  state() {
    return {
      tasks: loadTasks(),
      suppliers: SUPPLIERS
    }
  },
  getters: {
    taskList(state) {
      return state.tasks.map((task) => ({
        ...task,
        commentCount: countComments(task.comments),
        likeCount: countLikes(task.comments),
        summary: firstMainContent(task.comments)
      }))
    }
  },
  actions: {
    persist() {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(this.tasks))
    },
    getTask(id) {
      return this.tasks.find((item) => item.id === id)
    },
    createEmptyMain() {
      return createEmptyComment('main')
    },
    createChild(type, parentId) {
      return createEmptyComment(type, parentId)
    },
    saveDraft(payload) {
      const now = formatTime(new Date())
      if (payload.id) {
        const current = this.getTask(payload.id)
        if (!current) {
          throw new Error('任务不存在')
        }
        current.comments = cloneComments(payload.comments)
        current.updateTime = now
        this.persist()
        return current.id
      }
      const task = {
        id: generateId('task'),
        taskNo: generateTaskNo(),
        status: 'draft',
        supplierId: '',
        supplierName: '',
        createTime: now,
        updateTime: now,
        comments: cloneComments(payload.comments)
      }
      this.tasks.unshift(task)
      this.persist()
      return task.id
    },
    publishTask(id, supplierId) {
      const task = this.getTask(id)
      const supplier = this.suppliers.find((item) => item.id === supplierId)
      if (!task || !supplier) {
        throw new Error('任务或供应商不存在')
      }
      const applyOwner = (list) => {
        list.forEach((item) => {
          item.supplierId = supplier.id
          item.supplierName = supplier.name
          item.likeSupplierId = supplier.id
          item.likeSupplierName = supplier.name
          if (item.status === 'unpublished') {
            item.status = 'pending'
          }
          if (item.likeStatus === 'unpublished') {
            item.likeStatus = 'pending'
          }
          if (item.children && item.children.length) {
            applyOwner(item.children)
          }
        })
      }
      applyOwner(task.comments)
      task.supplierId = supplier.id
      task.supplierName = supplier.name
      task.status = task.status === 'draft' ? 'published' : task.status
      if (task.status === 'published') {
        task.status = 'processing'
      }
      task.updateTime = formatTime(new Date())
      this.persist()
      return task.id
    }
  }
})
