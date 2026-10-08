import { createRouter, createWebHistory } from 'vue-router'
import AppLayout from '@/layouts/AppLayout.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: AppLayout,
      redirect: '/tasks',
      children: [
        {
          path: 'tasks',
          name: 'TaskList',
          component: () => import('@/views/TaskList.vue'),
          meta: { title: '控评任务列表' }
        },
        {
          path: 'create',
          name: 'CommentCreate',
          component: () => import('@/views/CommentCreate.vue'),
          meta: { title: '创建评论点赞' }
        },
        {
          path: 'edit/:id',
          name: 'CommentEdit',
          component: () => import('@/views/CommentCreate.vue'),
          meta: { title: '编辑评论点赞' }
        },
        {
          path: 'publish/:id',
          name: 'TaskPublish',
          component: () => import('@/views/TaskPublish.vue'),
          meta: { title: '任务发布' }
        },
        {
          path: 'view/:id',
          name: 'TaskView',
          component: () => import('@/views/TaskView.vue'),
          meta: { title: '任务查看' }
        }
      ]
    }
  ]
})

export default router
