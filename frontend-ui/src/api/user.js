/**
 * 示例接口
 */

import { generate } from '@/libs/axios'
const config = {
  items: [
    // 登录接口
    {
      key: 'login',
      // url: '/frontend-ui/user/login',
      url: '/auth/admin/pass',
      method: 'post',
      showTips: true
    },
    // 获取用户角色接口
    {
      key: 'getRoles',
      // url: '/frontend-ui/user/getRoles',
      url: '/role/v1/getRoles',
      method: 'get',
      showTips: false
    }
  ]
}

export default {
  ...generate(config)
}
