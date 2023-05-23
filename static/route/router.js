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
    // , {
    //     name: 'settings',
    //     path: '/settings',
    //     component: Vue.defineAsyncComponent(() => loadModule('./pages/app-settings.vue', OPTIONS)),
    //     beforeEnter: [canUserAccess, tokenValid],
    // }, 
    {
        name: 'login',
        path: '/login',
        component: Vue.defineAsyncComponent(() => loadModule('./views/app-login.vue', OPTIONS))
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
        component: Vue.defineAsyncComponent(() => loadModule('./views/app-404.vue', OPTIONS))
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