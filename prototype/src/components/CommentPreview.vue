<template>
  <div>
    <div class="comment-row" :style="{ paddingLeft: `${16 + depth * 24}px` }">
      <el-tag :class="typeClass" effect="plain" size="small">{{ typeLabel }}</el-tag>
      <div class="comment-main">
        <div class="comment-content">{{ node.content || '（暂无内容）' }}</div>
        <img
          v-if="node.images && node.images.length"
          class="comment-thumb"
          :src="node.images[0]"
          alt="评论图片"
        />
      </div>
      <div class="comment-meta">
        <span v-if="node.likeCount" class="like-num">{{ node.likeCount }}赞</span>
        <span :class="statusClass">{{ statusLabel }}</span>
      </div>
      <div v-if="showOwner" class="owner-line">
        <div>评论：<strong>{{ statusLabel }}</strong> / {{ node.supplierName || '未分配' }}</div>
        <div>点赞：<strong>{{ likeStatusLabel }}</strong> / {{ node.likeSupplierName || '未分配' }}</div>
      </div>
    </div>
    <CommentPreview
      v-for="child in node.children"
      :key="child.id"
      :node="child"
      :depth="depth + 1"
      :show-owner="showOwner"
    />
  </div>
</template>

<script>
import { computed } from 'vue'
import { COMMENT_TYPES, ITEM_STATUS } from '@/constants'

export default {
  name: 'CommentPreview',
  props: {
    node: {
      type: Object,
      required: true
    },
    depth: {
      type: Number,
      default: 0
    },
    showOwner: {
      type: Boolean,
      default: false
    }
  },
  setup(props) {
    const typeLabel = computed(() => COMMENT_TYPES[props.node.type].label)
    const typeClass = computed(() => COMMENT_TYPES[props.node.type].className)
    const statusLabel = computed(() => ITEM_STATUS[props.node.status].label)
    const statusClass = computed(() => ITEM_STATUS[props.node.status].className)
    const likeStatusLabel = computed(() => ITEM_STATUS[props.node.likeStatus].label)
    return {
      typeLabel,
      typeClass,
      statusLabel,
      statusClass,
      likeStatusLabel
    }
  }
}
</script>
