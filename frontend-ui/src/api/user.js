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
    // 手机验证码登录
    {
      key: 'phoneLogin',
      // url: '/frontend-ui/user/login',
      url: '/auth/admin/sms',
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
    },
    // 获取客户信息
    {
      key: 'getInfo',
      url: '/admin/sysUser/getUserInfoByToken',
      method: 'post',
      showTips: true
    },
    // 发送短信验证码
    {
      key: 'sendVerityCode',
      url: '/admin/auth/getVerifyCode',
      method: 'post',
      showTips: true
    },
    // 登出
    {
      key: 'logout',
      url: '/user/logout',
      method: 'post',
      showTips: true
    },
  ]
}

export default {
  ...generate(config)
}
