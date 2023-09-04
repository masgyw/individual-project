/**
 * 权限-用户角色接口
 */
import { generate } from '@/libs/axios'
const config = {
  items: [
    {
      key: 'saveRole',
      url: '/admin/role/create',
      method: 'post',
    },

    {
      key: 'updateRole',
      url: '/admin/role/update',
      method: 'post',
    },

    {
      key: 'validRole',
      url: '/admin/role/valid/' + id,
      method: 'post'
    },

    {
      key: 'invalidRole',
      url: '/admin/role/invalid/' + id,
      method: 'post'
    },

    {
      key: 'findRolesByPage',
      url: '/admin/role/findByPage',
      method: 'post',
    },

    {
      key: 'findValidRoles',
      url: '/admin/role/findAllValidRoles',
      method: 'post',
    },

    {
      key: 'getUsersByRoleCode',
      url: '/admin/role/getAllUsersByRoleCode',
      method: 'post',
    },

    {
      key: 'getUserByRoleId',
      url: '/admin/role/getAllUsersByRoleId',
      method: 'post',
    },

    {
      key: 'getUserByRoleName',
      url: '/admin/role/getAllUsersByRoleName',
      method: 'post',
    },

    {
      key: 'grantRoleResources',
      url: '/admin/role/grantRoleResources',
      method: 'post',
    },

    {
      key: 'getResourceTreeByRoleId',
      url: '/admin/role/getResourceTreeById',
      method: 'post',
    },

    {
      key: 'getRoleResourceIds',
      url: '/admin/role/getRoleResourceIds/' + roleId,
      method: 'post'
    },

    {
      key: 'getAllValidRoles',
      url: '/admin/role/findAllValidRoles',
      method: 'post'
    }
  ]
}

export default {
  ...generate(config)
}