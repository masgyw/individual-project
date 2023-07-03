const routes = [
    {
        name: 'Dashboard',
        path: '/',
        component: Vue.defineAsyncComponent(() => loadModule('./layout/layout.vue', OPTIONS)),
        redirect: '/dashboard',
        meta: { title: '首页', roles: ['admin', 'editor'] },
        children: [
            {
                path: '/dashboard',
                name: 'Home',
                component: Vue.defineAsyncComponent(() => loadModule('./views/app-home.vue', OPTIONS)),
                beforeEnter: [canUserAccess, tokenValid],
                meta: { title: '主页面', roles: ['admin', 'editor'], icon: 'el-icon-menu' }
            },
            {
                name: 'House',
                path: '/house',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS))
            },
            {
                name: 'DataFile',
                path: '/data-file',
                component: Vue.defineAsyncComponent(() => loadModule('./views/data-file/data-file.vue', OPTIONS))
            },
        ]
    },
    {
        path: '/permission',
        component: Vue.defineAsyncComponent(() => loadModule('./layout/layout.vue', OPTIONS)),
        redirect: '/permission/list',
        name: '权限管理',
        meta: {
            title: '权限管理',
            icon: 'form'
        },
        children: [
            {
                path: 'list',
                name: '资源列表',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '资源列表', icon: 'form' }
            },
            {
                path: 'add',
                name: '资源添加',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '资源添加', icon: 'form' }
            },
            {
                path: 'update',
                name: '资源修改',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '资源修改', icon: 'form' },
                hidden: true
            },
            {
                path: 'roleList',
                name: '角色管理',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '角色管理', icon: 'form' }
            },
            {
                path: 'platformList',
                name: '平台管理',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '平台管理', icon: 'form' }
            },
            {
                path: 'userList',
                name: '后台用户列表',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '后台用户列表', icon: 'form' }
            }
        ]
    },
    {
        path: '/template',
        component: Vue.defineAsyncComponent(() => loadModule('./layout/layout.vue', OPTIONS)),
        redirect: '/template/template',
        name: '模板管理',
        meta: {
            title: '模板管理',
            icon: 'form'
        },
        children: [
            {
                path: 'templateCategory',
                name: '模板类别',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '模板类别', icon: 'form' }
            },
            {
                path: 'template',
                name: '模板管理',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '模板管理', icon: 'form' }
            },
            {
                path: 'templateItem',
                name: '模板项管理',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '模板项管理', icon: 'form' }
            },
            {
                path: 'selectDict',
                name: '下拉字典管理',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house/house.vue', OPTIONS)),
                meta: { title: '下拉字典管理', icon: 'form' }
            }
        ]
    },
    {
        name: 'login',
        path: '/login',
        component: Vue.defineAsyncComponent(() => loadModule('./views/app-login.vue', OPTIONS)),
        meta: { title: '登录', icon: 'form' }
    }, 
    // {
    //     name: 'signup',
    //     path: '/signup',
    //     component: Vue.defineAsyncComponent(() => loadModule('./pages/app-signup.vue', OPTIONS))
    // }, {
    //     name: 'reset-password',
    //     path: '/reset-password',
    //     component: Vue.defineAsyncComponent(() => loadModule('./pages/app-reset-password.vue', OPTIONS))
    // }, 
    {
        name: '404',
        path: '/:pathMatch(.*)',
        component: Vue.defineAsyncComponent(() => loadModule('./views/app-404.vue', OPTIONS)),
        meta: { title: '404', icon: 'form' }
    }
]

const router = VueRouter.createRouter({
    // hash mode url 会添加 /#/xxx
    history: VueRouter.createWebHashHistory(),
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    // history: VueRouter.createWebHistory(),
    routes, // short for `routes: routes`
})

router.beforeEach((to, from, next) => {
    // console.log('router beforeEach:{}-{}', from, to)
    // For read current uri after each redirect
    if (to.matched.some(record => record.meta.reuse === false)) {
        app.key = to.path
    } else {
        app.key = null
    }
    next()
})

router.afterEach((to, from) => {
    // console.log('router afterEach:{}-{}', from, to)
    // To close sidepanel after navigatiokn
    let closePanelBtn = document.getElementById("sidepanel-close")
    if ((closePanelBtn) && (to.path !== from.path)) {
        closePanelBtn.click();
    }
});