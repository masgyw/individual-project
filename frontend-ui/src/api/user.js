/**
 * 示例接口
 */

import { generate } from '@/libs/axios'
const config = {
  items: [
    // 登录接口
    {
      key: 'login',
      url: '/frontend-ui/user/login',
      method: 'post',
      showTips: true
    },
    // 获取用户角色接口
    {
      key: 'getRoles',
      url: '/frontend-ui/user/getRoles',
      method: 'get',
      showTips: false
    }
  ]
}

export default {
  ...generate(config)
}
