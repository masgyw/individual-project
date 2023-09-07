import { generate } from '@/libs/axios'
const config = {
    items: [
        {
            key: 'findAllUsersByPage',
            url: '/adminUser/v1/findByPage',
            method: 'post',
        },
        {
            key: 'modifyPass',
            url: '/adminUser/v1/modifyPass',
            method: 'post',
        },
        {
            key: 'validUser',
            url: '/adminUser/v1/valid',
            method: 'post'
        },
        {
            key: 'invalidUser',
            url: '/adminUser/v1/invalid',
            method: 'post'
        },
        {
            key: 'findCurrentUserAllRoles',
            url: '/adminUser/v1/getCurrentUserAllRolesByUserId',
            method: 'post',
        },
        {
            key: 'create',
            url: '/adminUser/v1/createAdminUser',
            method: 'post',
        },
        {
            key: 'findUserPlatforms',
            url: '/adminUser/v1/getCurrentUserPlatforms',
            method: 'post',
        },
        {
            key: 'findMenuTree',
            url: '/adminUser/v1/getCurrentUserResourceMenuTree',
            method: 'post',
        },

        {
            key: 'grantUserRoles',
            url: '/adminUser/v1/grantUserRoles',
            method: 'post',
        },

        {
            key: 'findUserAllRoles',
            url: '/adminUser/v1/getUserAllRolesByUserId',
            method: 'post'
        },

        {
            key: 'findUserPlatformsById',
            url: '/adminUser/v1/getUserPlatforms',
            method: 'post'
        },
        {
            key: 'getUserRoles',
            url: '/adminUser/v1/getUserRoles',
            method: 'post'
        },
        {
            key: 'getUserPlatforms',
            url: '/adminUser/v1/getUserPlatforms',
            method: 'post'
        },
        {
            key: 'grantUserPlatforms',
            url: '/adminUser/v1/grantUserPlatforms',
            method: 'post',
        }
    ]
}

export default {
    ...generate(config)
}
