(function (global) {
  var STORAGE_KEY = 'kp-html-prototype-v1'
  var PLACEHOLDER =
    'data:image/svg+xml;utf8,' +
    encodeURIComponent(
      '<svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 72 72"><rect width="72" height="72" rx="6" fill="#f2f3f5"/><rect x="10" y="16" width="52" height="40" rx="5" fill="#e4e7ed"/><circle cx="26" cy="32" r="6" fill="#c0c4cc"/><path d="M14 50l14-14 10 10 8-8 12 12v4H14z" fill="#909399"/></svg>'
    )

  var TYPE_MAP = {
    main: { label: '新增主评', cls: 'tag-main' },
    existMain: { label: '已有主评', cls: 'tag-main' },
    reply: { label: '回复', cls: 'tag-reply' },
    stack: { label: '叠楼', cls: 'tag-stack' },
    mainStack: { label: '主评叠楼', cls: 'tag-main-stack' }
  }

  var STATUS_MAP = {
    unpublished: { label: '未发布', cls: 'st-unpublished' },
    pending: { label: '待完成', cls: 'st-pending' },
    reviewing: { label: '待验收', cls: 'st-reviewing' },
    completed: { label: '已完成', cls: 'st-completed' }
  }

  var TASK_STATUS_MAP = {
    draft: { label: '草稿', cls: 'task-st-draft' },
    published: { label: '已发布', cls: 'task-st-published' },
    processing: { label: '进行中', cls: 'task-st-processing' },
    completed: { label: '已完成', cls: 'task-st-completed' }
  }

  var SUPPLIERS = [
    { id: 'yuandong', name: '远东' },
    { id: 'xingyan', name: '星颜' },
    { id: 'yanda', name: '彦达' }
  ]

  function uid(prefix) {
    return (prefix || 'id') + '-' + Date.now().toString(36) + '-' + Math.random().toString(36).slice(2, 7)
  }

  function nowText() {
    var d = new Date()
    var p = function (n) { return (n < 10 ? '0' : '') + n }
    return d.getFullYear() + '-' + p(d.getMonth() + 1) + '-' + p(d.getDate()) + ' ' + p(d.getHours()) + ':' + p(d.getMinutes())
  }

  function taskNo() {
    return 'KP' + nowText().replace(/[-: ]/g, '')
  }

  function emptyComment(type, parentId) {
    return {
      id: uid(type),
      type: type,
      parentId: parentId || '',
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

  function comment(data) {
    var item = emptyComment(data.type, data.parentId)
    Object.keys(data).forEach(function (key) {
      item[key] = data[key]
    })
    item.children = data.children || []
    return item
  }

  function demoTasks() {
    return [
      {
        id: 'task-demo-001',
        taskNo: 'KP20261008001',
        status: 'processing',
        supplierId: 'xingyan',
        supplierName: '星颜',
        interval: 5,
        createTime: '2026-10-07 14:20',
        updateTime: '2026-10-08 09:10',
        comments: [
          comment({
            id: 'c-main-1',
            type: 'main',
            content: '这款精华用了第三次，肤感很好，控油也很棒！',
            images: [PLACEHOLDER],
            likeCount: 0,
            status: 'completed',
            supplierId: 'xingyan',
            supplierName: '星颜',
            likeStatus: 'completed',
            likeSupplierId: 'xingyan',
            likeSupplierName: '星颜',
            children: [
              comment({
                id: 'c-reply-1',
                type: 'reply',
                parentId: 'c-main-1',
                content: '同感回购+1，真的越用越喜欢～',
                images: [PLACEHOLDER],
                likeCount: 199,
                status: 'reviewing',
                supplierId: 'xingyan',
                supplierName: '星颜',
                likeStatus: 'reviewing',
                likeSupplierId: 'xingyan',
                likeSupplierName: '星颜',
                children: [
                  comment({
                    id: 'c-stack-1',
                    type: 'stack',
                    parentId: 'c-reply-1',
                    content: '包装也很好看。',
                    status: 'pending',
                    supplierId: 'xingyan',
                    supplierName: '星颜',
                    likeStatus: 'pending',
                    likeSupplierId: 'xingyan',
                    likeSupplierName: '星颜'
                  }),
                  comment({
                    id: 'c-ms-1',
                    type: 'mainStack',
                    parentId: 'c-reply-1',
                    content: '客服态度很好，主评账号来叠楼也很自然。',
                    status: 'unpublished',
                    supplierId: 'xingyan',
                    supplierName: '星颜',
                    likeStatus: 'unpublished',
                    likeSupplierId: 'xingyan',
                    likeSupplierName: '星颜'
                  })
                ]
              }),
              comment({
                id: 'c-reply-2',
                type: 'reply',
                parentId: 'c-main-1',
                content: '请问干皮也能用吗？求真实反馈。',
                status: 'completed',
                supplierId: 'xingyan',
                supplierName: '星颜',
                likeStatus: 'completed',
                likeSupplierId: 'xingyan',
                likeSupplierName: '星颜',
                children: [
                  comment({
                    id: 'c-stack-2',
                    type: 'stack',
                    parentId: 'c-reply-2',
                    content: '干皮可以用，注意后续做好保湿。',
                    status: 'reviewing',
                    supplierId: 'xingyan',
                    supplierName: '星颜',
                    likeStatus: 'reviewing',
                    likeSupplierId: 'xingyan',
                    likeSupplierName: '星颜'
                  })
                ]
              }),
              comment({
                id: 'c-reply-3',
                type: 'reply',
                parentId: 'c-main-1',
                content: '楼上的，我是干皮用着也不错，保湿够。',
                status: 'pending',
                supplierId: 'xingyan',
                supplierName: '星颜',
                likeStatus: 'pending',
                likeSupplierId: 'xingyan',
                likeSupplierName: '星颜'
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
        interval: 3,
        createTime: '2026-10-08 10:05',
        updateTime: '2026-10-08 10:05',
        comments: [
          comment({
            id: 'c-main-2',
            type: 'main',
            content: '油皮亲测不闷痘，夏天用闭口少了很多！',
            images: [PLACEHOLDER],
            likeCount: 0,
            children: [
              comment({
                id: 'c-reply-4',
                type: 'reply',
                parentId: 'c-main-2',
                content: '油皮表示闭口确实少了，脸也稳定。',
                likeCount: 0,
                status: 'completed',
                children: [
                  comment({
                    id: 'c-ms-2',
                    type: 'mainStack',
                    parentId: 'c-reply-4',
                    content: '油皮亲测，闭口确实少了。',
                    likeCount: 50,
                    status: 'reviewing'
                  })
                ]
              }),
              comment({
                id: 'c-reply-5',
                type: 'reply',
                parentId: 'c-main-2',
                content: '这个价位还有活动吗？蹲一个。',
                status: 'pending'
              }),
              comment({
                id: 'c-reply-6',
                type: 'reply',
                parentId: 'c-main-2',
                content: '用了两周，皮肤状态稳定多了。',
                status: 'unpublished'
              })
            ]
          })
        ]
      }
    ]
  }

  function loadTasks() {
    try {
      var raw = localStorage.getItem(STORAGE_KEY)
      if (raw) {
        var parsed = JSON.parse(raw)
        if (parsed && parsed.length) return parsed
      }
    } catch (e) {}
    return demoTasks()
  }

  function saveTasks(tasks) {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks))
  }

  function flatten(list, result) {
    result = result || []
    ;(list || []).forEach(function (item) {
      result.push(item)
      if (item.children && item.children.length) flatten(item.children, result)
    })
    return result
  }

  function getTask(id) {
    return loadTasks().filter(function (item) { return item.id === id })[0]
  }

  function upsertTask(task) {
    var tasks = loadTasks()
    var found = false
    tasks = tasks.map(function (item) {
      if (item.id === task.id) {
        found = true
        return task
      }
      return item
    })
    if (!found) tasks.unshift(task)
    saveTasks(tasks)
    return task
  }

  function queryId() {
    var match = location.search.match(/[?&]id=([^&]+)/)
    return match ? decodeURIComponent(match[1]) : ''
  }

  function escapeHtml(text) {
    return String(text == null ? '' : text)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
  }

  function renderNav(active) {
    var links = [
      { href: 'list.html', key: 'list', text: '控评任务列表' },
      { href: 'create.html', key: 'create', text: '创建评论点赞' },
      { href: 'publish.html', key: 'publish', text: '任务发布' },
      { href: 'view.html', key: 'view', text: '任务查看' }
    ]
    return (
      '<div class="top-nav"><div class="brand">控评原型</div><div class="nav-links">' +
      links.map(function (item) {
        return '<a class="' + (item.key === active ? 'active' : '') + '" href="' + item.href + '">' + item.text + '</a>'
      }).join('') +
      '</div></div>'
    )
  }

  function renderTree(comments, options) {
    options = options || {}
    var html = ''
    function walk(list, depth) {
      ;(list || []).forEach(function (item) {
        var type = TYPE_MAP[item.type] || TYPE_MAP.main
        var status = STATUS_MAP[item.status] || STATUS_MAP.unpublished
        var likeStatus = STATUS_MAP[item.likeStatus] || STATUS_MAP.unpublished
        var img = item.images && item.images[0]
          ? '<img class="thumb" src="' + item.images[0] + '" alt="">'
          : ''
        var like = item.likeCount ? '<span class="like">' + item.likeCount + '赞</span>' : ''
        var owner = options.showOwner
          ? '<div class="owner">评论：<b>' + status.label + '</b> / ' + (item.supplierName || '未分配') +
            '<br>点赞：<b>' + likeStatus.label + '</b> / ' + (item.likeSupplierName || '未分配') + '</div>'
          : ''
        html +=
          '<div class="row indent-' + depth + '">' +
            '<input class="checkbox" type="checkbox">' +
            '<span class="tag ' + type.cls + '">' + type.label + '</span>' +
            '<div class="row-mid"><div class="content-text">' + escapeHtml(item.content || '（暂无内容）') + '</div>' + img + '</div>' +
            like +
            '<span class="status ' + status.cls + '">' + status.label + '</span>' +
            owner +
          '</div>'
        if (item.children && item.children.length) walk(item.children, Math.min(depth + 1, 4))
      })
    }
    walk(comments, 2)
    return html
  }

  function applySupplier(list, supplier) {
    ;(list || []).forEach(function (item) {
      item.supplierId = supplier.id
      item.supplierName = supplier.name
      item.likeSupplierId = supplier.id
      item.likeSupplierName = supplier.name
      if (item.status === 'unpublished') item.status = 'pending'
      if (item.likeStatus === 'unpublished') item.likeStatus = 'pending'
      applySupplier(item.children, supplier)
    })
  }

  function validateComments(list) {
    for (var i = 0; i < (list || []).length; i++) {
      if (!list[i].content || !String(list[i].content).trim()) return false
      if (list[i].children && list[i].children.length && !validateComments(list[i].children)) return false
    }
    return true
  }

  global.KP = {
    PLACEHOLDER: PLACEHOLDER,
    TYPE_MAP: TYPE_MAP,
    STATUS_MAP: STATUS_MAP,
    TASK_STATUS_MAP: TASK_STATUS_MAP,
    SUPPLIERS: SUPPLIERS,
    uid: uid,
    nowText: nowText,
    taskNo: taskNo,
    emptyComment: emptyComment,
    loadTasks: loadTasks,
    saveTasks: saveTasks,
    flatten: flatten,
    getTask: getTask,
    upsertTask: upsertTask,
    queryId: queryId,
    escapeHtml: escapeHtml,
    renderNav: renderNav,
    renderTree: renderTree,
    applySupplier: applySupplier,
    validateComments: validateComments
  }
})(window)
