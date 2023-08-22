/**
 * 示例接口
 */

import { generate } from '@/libs/axios'
const config = {
  items: [
    // 登录接口
    {
      key: 'login',
      url: '/auth/admin/pass',
      method: 'post',
      showTips: true
    },
    // 获取用户角色接口
    {
      key: 'getRoles',
      url: '/role/v1/findByPage',
      method: 'get',
      showTips: false
    }
  ]
}

export default {
  ...generate(config)
}
