<template>
  <div class="editor-card">
    <div class="editor-head">
      <el-tag :class="typeClass" effect="plain">{{ typeLabel }}</el-tag>
      <div class="editor-actions">
        <el-button v-if="node.type === 'main'" size="small" @click="handleAddReply">
          新增回复
        </el-button>
        <el-button v-if="node.type === 'reply'" size="small" @click="handleAddStack">
          新增叠楼
        </el-button>
        <el-button v-if="node.type === 'reply'" size="small" @click="handleAddMainStack">
          新增主评叠楼
        </el-button>
        <el-button size="small" type="danger" text @click="$emit('remove')">
          删除
        </el-button>
      </div>
    </div>
    <el-input
      v-model="node.content"
      type="textarea"
      :rows="3"
      maxlength="200"
      show-word-limit
      placeholder="请输入评论内容"
    />
    <div style="display: flex; align-items: flex-start; gap: 16px; margin-top: 12px;">
      <el-upload
        :file-list="fileList"
        :auto-upload="false"
        list-type="picture-card"
        accept="image/*"
        :limit="3"
        :on-change="handleUploadChange"
        :on-remove="handleUploadRemove"
      >
        <span>上传图片</span>
      </el-upload>
      <div>
        <div style="color: #86909c; font-size: 12px; margin-bottom: 6px;">点赞数</div>
        <el-input-number v-model="node.likeCount" :min="0" :max="99999" />
      </div>
    </div>
    <div v-if="node.children && node.children.length" class="child-wrap">
      <CommentEditor
        v-for="(child, index) in node.children"
        :key="child.id"
        :node="child"
        @remove="removeChild(index)"
      />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { COMMENT_TYPES } from '@/constants'
import { useTaskStore } from '@/stores/taskStore'

export default {
  name: 'CommentEditor',
  props: {
    node: {
      type: Object,
      required: true
    }
  },
  emits: ['remove'],
  setup(props) {
    const store = useTaskStore()
    const typeLabel = computed(() => COMMENT_TYPES[props.node.type].label)
    const typeClass = computed(() => COMMENT_TYPES[props.node.type].className)
    const fileList = computed(() => {
      return (props.node.images || []).map((url, index) => ({
        name: `image-${index}`,
        url
      }))
    })

    const readFile = (file) => {
      return new Promise((resolve) => {
        const reader = new FileReader()
        reader.onload = () => resolve(reader.result)
        reader.readAsDataURL(file.raw)
      })
    }

    const handleUploadChange = async (uploadFile, uploadFiles) => {
      const images = []
      for (let i = 0; i < uploadFiles.length; i++) {
        const current = uploadFiles[i]
        if (current.url) {
          images.push(current.url)
        } else if (current.raw) {
          images.push(await readFile(current))
        }
      }
      props.node.images = images
    }

    const handleUploadRemove = (uploadFile, uploadFiles) => {
      props.node.images = uploadFiles
        .map((item) => item.url)
        .filter((item) => item)
    }

    const handleAddReply = () => {
      props.node.children.push(store.createChild('reply', props.node.id))
    }

    const handleAddStack = () => {
      props.node.children.push(store.createChild('stack', props.node.id))
    }

    const handleAddMainStack = () => {
      props.node.children.push(store.createChild('mainStack', props.node.id))
    }

    const removeChild = (index) => {
      props.node.children.splice(index, 1)
    }

    return {
      typeLabel,
      typeClass,
      fileList,
      handleUploadChange,
      handleUploadRemove,
      handleAddReply,
      handleAddStack,
      handleAddMainStack,
      removeChild
    }
  }
}
</script>
