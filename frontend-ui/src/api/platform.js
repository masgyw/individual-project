/**
 * 权限-平台接口
 */
 import { generate } from '@/libs/axios'

 // 先不加前缀 /admin
 const config = {
    items: [
      {
        key: 'savePlatform',
        url: '/platform/v1/create',
        method: 'post',
        showTips: true
      },
      {
        key: 'updatePlatform',
        url: '/platform/v1/update',
        method: 'post',
        showTips: true
      },
      {
        key: 'findPlatformByPage',
        url: '/platform/v1/findByPage',
        method: 'post',
        showTips: true
      },
      {
        key: 'findValidPlatforms',
        url: '/platform/v1/findAll',
        method: 'post',
        showTips: true
      },
      {
        key: 'validPlatform',
        url: '/platform/v1/valid',
        method: 'post',
        showTips: true
      },
      {
        key: 'invalidPlatform',
        url: '/platform/v1/invalid',
        method: 'post',
        showTips: true
      }
    ]
  }

 export default {
    ...generate(config)
  }
  