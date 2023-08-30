/**
 * 权限-平台接口
 */
 import { generate } from '@/libs/axios'

 const config = {
    items: [
      {
        key: 'savePlatform',
        url: '/admin/platform/create',
        method: 'post',
        showTips: true
      },
      {
        key: 'updatePlatform',
        url: '/admin/platform/update',
        method: 'post',
        showTips: true
      },
      {
        key: 'findPlatformByPage',
        url: '/admin/platform/findByPage',
        method: 'post',
        showTips: true
      },
      {
        key: 'findValidPlatforms',
        url: '/admin/platform/findAll',
        method: 'post',
        showTips: true
      },
      {
        key: 'validPlatform',
        url: '/admin/platform/valid',
        method: 'post',
        showTips: true
      },
      {
        key: 'invalidPlatform',
        url: '/admin/platform/invalid',
        method: 'post',
        showTips: true
      }
    ]
  }

 export default {
    ...generate(config)
  }
  