export const COMMENT_TYPES = {
  main: {
    label: '主评',
    tagType: '',
    className: 'type-main'
  },
  reply: {
    label: '回复',
    tagType: 'success',
    className: 'type-reply'
  },
  stack: {
    label: '叠楼',
    tagType: 'warning',
    className: 'type-stack'
  },
  mainStack: {
    label: '主评叠楼',
    tagType: '',
    className: 'type-main-stack'
  }
}

export const ITEM_STATUS = {
  unpublished: { label: '未发布', className: 'status-unpublished' },
  pending: { label: '待完成', className: 'status-pending' },
  reviewing: { label: '待验收', className: 'status-reviewing' },
  completed: { label: '已完成', className: 'status-completed' }
}

export const TASK_STATUS = {
  draft: { label: '草稿', type: 'info' },
  published: { label: '已发布', type: 'warning' },
  processing: { label: '进行中', type: '' },
  completed: { label: '已完成', type: 'success' }
}

export const SUPPLIERS = [
  { id: 's-yuandong', name: '远东供应商' },
  { id: 's-xingyan', name: '星颜供应商' },
  { id: 's-yanda', name: '彦达供应商' }
]

export const DEMO_IMAGE =
  'data:image/svg+xml;utf8,' +
  encodeURIComponent(`
  <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 72 72">
    <rect width="72" height="72" rx="8" fill="#f1f3f5"/>
    <rect x="10" y="16" width="52" height="40" rx="6" fill="#dee2e6"/>
    <circle cx="26" cy="32" r="6" fill="#adb5bd"/>
    <path d="M14 50l14-14 10 10 8-8 12 12v4H14z" fill="#868e96"/>
  </svg>`)

function buildComment(partial) {
  return {
    id: partial.id,
    type: partial.type,
    parentId: partial.parentId || null,
    content: partial.content,
    images: partial.images || [],
    likeCount: partial.likeCount || 0,
    status: partial.status || 'unpublished',
    supplierId: partial.supplierId || '',
    supplierName: partial.supplierName || '',
    likeStatus: partial.likeStatus || 'unpublished',
    likeSupplierId: partial.likeSupplierId || '',
    likeSupplierName: partial.likeSupplierName || '',
    children: partial.children || []
  }
}

export const DEMO_TASKS = [
  {
    id: 'task-demo-001',
    taskNo: 'KP20261008001',
    status: 'processing',
    supplierId: 's-xingyan',
    supplierName: '星颜供应商',
    createTime: '2026-10-07 14:20',
    updateTime: '2026-10-08 09:10',
    comments: [
      buildComment({
        id: 'c-main-1',
        type: 'main',
        content: '这款精华用了第三次，肤感很好，控油也很棒！',
        images: [DEMO_IMAGE],
        likeCount: 128,
        status: 'completed',
        supplierId: 's-xingyan',
        supplierName: '星颜供应商',
        likeStatus: 'completed',
        likeSupplierId: 's-xingyan',
        likeSupplierName: '星颜供应商',
        children: [
          buildComment({
            id: 'c-reply-1',
            type: 'reply',
            parentId: 'c-main-1',
            content: '同感回购+1，真的越用越喜欢～',
            images: [DEMO_IMAGE],
            likeCount: 199,
            status: 'reviewing',
            supplierId: 's-xingyan',
            supplierName: '星颜供应商',
            likeStatus: 'reviewing',
            likeSupplierId: 's-xingyan',
            likeSupplierName: '星颜供应商',
            children: [
              buildComment({
                id: 'c-stack-1',
                type: 'stack',
                parentId: 'c-reply-1',
                content: '包装也很好看，质感在线。',
                likeCount: 36,
                status: 'pending',
                supplierId: 's-xingyan',
                supplierName: '星颜供应商',
                likeStatus: 'pending',
                likeSupplierId: 's-xingyan',
                likeSupplierName: '星颜供应商'
              }),
              buildComment({
                id: 'c-main-stack-1',
                type: 'mainStack',
                parentId: 'c-reply-1',
                content: '客服回复很快，主评账号来叠楼也很自然。',
                likeCount: 12,
                status: 'unpublished',
                supplierId: 's-xingyan',
                supplierName: '星颜供应商',
                likeStatus: 'unpublished',
                likeSupplierId: 's-xingyan',
                likeSupplierName: '星颜供应商'
              })
            ]
          }),
          buildComment({
            id: 'c-reply-2',
            type: 'reply',
            parentId: 'c-main-1',
            content: '请问干皮也能用吗？求真实反馈。',
            likeCount: 8,
            status: 'completed',
            supplierId: 's-xingyan',
            supplierName: '星颜供应商',
            likeStatus: 'completed',
            likeSupplierId: 's-xingyan',
            likeSupplierName: '星颜供应商',
            children: [
              buildComment({
                id: 'c-stack-2',
                type: 'stack',
                parentId: 'c-reply-2',
                content: '干皮可以用，注意后续做好保湿。',
                likeCount: 21,
                status: 'reviewing',
                supplierId: 's-xingyan',
                supplierName: '星颜供应商',
                likeStatus: 'reviewing',
                likeSupplierId: 's-xingyan',
                likeSupplierName: '星颜供应商'
              })
            ]
          }),
          buildComment({
            id: 'c-reply-3',
            type: 'reply',
            parentId: 'c-main-1',
            content: '楼上的，我是干皮用着也不错，保湿够。',
            likeCount: 5,
            status: 'pending',
            supplierId: 's-xingyan',
            supplierName: '星颜供应商',
            likeStatus: 'pending',
            likeSupplierId: 's-xingyan',
            likeSupplierName: '星颜供应商'
          })
        ]
      })
    ]
  },
  {
    id: 'task-demo-002',
    taskNo: 'KP20261008002',
    status: 'draft',
    supplierId: '',
    supplierName: '',
    createTime: '2026-10-08 10:05',
    updateTime: '2026-10-08 10:05',
    comments: [
      buildComment({
        id: 'c-main-2',
        type: 'main',
        content: '油皮亲测不闷痘，夏天用闭口少了很多！',
        images: [DEMO_IMAGE],
        likeCount: 50,
        children: [
          buildComment({
            id: 'c-reply-4',
            type: 'reply',
            parentId: 'c-main-2',
            content: '油皮表示闭口确实少了，脸也稳定。',
            likeCount: 18
          })
        ]
      })
    ]
  }
]
