/**
 * 权限-用户资源接口
 */
import { generate } from '@/libs/axios'
const config = {
  items: [
    // 分页查询接口
    {
      key: 'findByPage',
      url: '/admin/resource/findByPage',
      method: 'post',
      showTips: true
    }
  ]
}

export default {
  ...generate(config)
}
