/**
 * 权限-用户资源接口
 */
import { generate } from '@/libs/axios'
const config = {
  items: [
    // 分页查询接口
    {
      key: 'findByPage',
      url: '/resource/v1/findByPage',
      method: 'post',
      showTips: true
    },
    {
      key: 'saveResource',
      url: '/resource/v1/createResource',
      method: 'post',
      showTips: true
    },
    {
      key: 'getResourceTree',
      url: '/resource/v1/getResourceTreeByPlatform',
      method: 'post',
      showTips: true,
      pathParam: true
    },

  ]
}

export default {
  ...generate(config)
}
