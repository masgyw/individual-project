// 权限管理
import Layout from '@/layout'
export default {
  path: '/permission',
  component: Layout,
  redirect: '/permission/list',
  name: '权限管理',
  meta: {
    title: '权限管理',
    icon: 'el-icon-form',
    roles: ['admin', 'editor']
  },
  isCollapse: true,
  children: [
    {
      path: '/permission/list',
      name: '资源列表',
      component: () => import('@/views/resource/resourceList'),
      meta: { title: '资源列表', icon: 'form', roles: ['admin', 'editor'] },
    },
    {
      path: 'add',
      name: '资源添加',
      component: () => import('@/views/resource/resourceAdd'),
      meta: { title: '资源添加', icon: 'form', roles: ['admin', 'editor'] }
    },
    // {
    //   path: 'update',
    //   name: '资源修改',
    //   component: () => import('@/views/resource/resourceUpdate'),
    //   meta: { title: '资源修改', icon: 'form' },
    //   hidden: true
    // },
    // {
    //   path: 'roleList',
    //   name: '角色管理',
    //   component: () => import('@/views/role/roleList'),
    //   meta: { title: '角色管理', icon: 'form' }
    // },
    // {
    //   path: 'platformList',
    //   name: '平台管理',
    //   component: () => import('@/views/platform/platformList'),
    //   meta: { title: '平台管理', icon: 'form' }
    // },
    // {
    //   path: 'userList',
    //   name: '后台用户列表',
    //   component: () => import('@/views/user/userList'),
    //   meta: { title: '后台用户列表', icon: 'form' }
    // }
  ]
}
