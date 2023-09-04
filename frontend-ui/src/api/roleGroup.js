import { generate } from '@/libs/axios'
const config = {
  items: [
    {
      key: 'saveRoleGroup',
      url: '/admin/roleGroup/createRoleGroup',
      method: 'post',
    },
    {
      key: 'updateRoleGroup',
      url: '/admin/roleGroup/updateRoleGroup',
      method: 'post',
    },
    {
      key: 'validRoleGroup',
      url: '/admin/roleGroup/validRoleGroup/' + id,
      method: 'post'
    },
    {
      key: 'invalidRoleGroup',
      url: '/admin/roleGroup/invalidRoleGroup/' + id,
      method: 'post'
    },
    {
      key: 'findRoleGroupByPage',
      url: '/admin/roleGroup/findAllRoleGroupsByPage',
      method: 'post',
    },
    {
      key: 'findValidRoleGroups',
      url: '/admin/roleGroup/findAllValidRoleGroups',
      method: 'post',
    }
  ]
}

export default {
  ...generate(config)
}