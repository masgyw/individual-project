const APP_TOKEN = 'app_token';
const FREE_ACCESS_PAGES = ['login', 'signup', 'reset-password', '404'];
const API_ROUTES = {
    root: 'http://localhost:8080',
    auth: 'http://localhost:8080/api/auth',
    refresh: 'http://localhost:8080/api/token/refresh',
    me: 'http://localhost:8080/api/me',
    users: 'http://localhost:8080/api/users',
}

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};

// Functions
// Router Guards
async function canUserAccess(to, from, next) {

    var app_token = localStorage.getItem(APP_TOKEN)

    if ((!app_token) && (to.name !== 'login')) next({ name: 'login' })
    else next();
}

async function tokenValid(to, from, next) {
    var app_token = localStorage.getItem(APP_TOKEN)
    if (app_token) app_token = JSON.parse(app_token) // Parse the json string value

    var options = {
        headers: {
            'Authorization': `Bearer ${(app_token ? app_token.token : '')}`
        }
    }

    var mustRefresh = null;
    if (app_token) {
        const canAccess = await axios.get(API_ROUTES.me, options)
            .catch((error) => {
                let message = typeof error.response !== 'undefined' ? error.response.data.message : error.message;
                // localStorage.removeItem(APP_TOKEN)
                // store.commit('LOGIN_FAILLURE');
                // next({ name: 'login' });
                mustRefresh = true;
            }).finally(() => {
                if (mustRefresh) {
                    // Refresh token
                    return axios.post(API_ROUTES.refresh, { "refresh_token": app_token.refresh_token })
                        .then((result) => {
                            store.commit('LOGIN_SUCCESS', result.data);
                            next({ name: to.name });
                        })
                        .catch((error) => {
                            let message = typeof error.response !== 'undefined' ? error.response.data.message : error.message;
                            localStorage.removeItem(APP_TOKEN);
                            store.commit('LOGIN_FAILLURE');
                            next({ name: 'login' });
                        })
                }
            })

        if (
            (typeof canAccess.data !== 'undefined')
            &&
            (to.name !== 'login')
        ) {
            store.commit('LOGIN_SUCCESS', app_token);
            next()
        } else next({ name: 'login' });
    }
}

// Start vue3-sfc-loader
const OPTIONS = {
    moduleCache: {
        vue: Vue
    },
    async getFile(url) {

        const res = await fetch(url);
        if (!res.ok)
            throw Object.assign(new Error(res.statusText + ' ' + url), {
                res
            });
        return {
            getContentData: asBinary => asBinary ? res.arrayBuffer() : res.text(),
        }
    },
    addStyle(textContent) {
        const style = Object.assign(document.createElement('style'), {
            textContent
        });
        const ref = document.head.getElementsByTagName('style')[0] || null;
        document.head.insertBefore(style, ref);
    },
}
const { loadModule } = window['vue3-sfc-loader'];
// End vue3-sfc-loader

const store = new Vuex.Store({
    state() {
        return {
            isConnected: null,
            access_token: null,
        }
    },
    mutations: {
        LOGIN_SUCCESS(state, payload) {
            state.isConnected = true;
            state.access_token = payload;

            if (payload) localStorage.setItem(APP_TOKEN, JSON.stringify(payload));
        },
        LOGIN_FAILLURE(state, payload) {
            state.isConnected, state.access_token = null;
            localStorage.removeItem(APP_TOKEN)
        }
    }
})

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
                // beforeEnter: [canUserAccess, tokenValid],
                meta: { title: '主页面', roles: ['admin', 'editor'], icon: 'el-icon-menu' }
            },
            {
                name: 'House',
                path: '/house',
                component: Vue.defineAsyncComponent(() => loadModule('./views/house.vue', OPTIONS))
            },
            {
                name: 'DataFile',
                path: '/data-file',
                component: Vue.defineAsyncComponent(() => loadModule('index.vue', OPTIONS))
            },
        ]
    },
    // , {
    //     name: 'settings',
    //     path: '/settings',
    //     component: Vue.defineAsyncComponent(() => loadModule('./pages/app-settings.vue', OPTIONS)),
    //     beforeEnter: [canUserAccess, tokenValid],
    // }, {
    //     name: 'login',
    //     path: '/login',
    //     component: Vue.defineAsyncComponent(() => loadModule('./pages/app-login.vue', OPTIONS))
    // }, {
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
    // 4. Provide the history implementation to use. We are using the hash history for simplicity here.
    history: VueRouter.createWebHistory(),
    routes, // short for `routes: routes`
})

router.beforeEach((to, from, next) => {
    console.log('router beforeEach:{}-{}', from, to)
    // For read current uri after each redirect
    if (to.matched.some(record => record.meta.reuse === false)) {
        app.key = to.path
    } else {
        app.key = null
    }
    next()
})

router.afterEach((to, from) => {
    console.log('router afterEach:{}-{}', from, to)
    // To close sidepanel after navigatiokn
    let closePanelBtn = document.getElementById("sidepanel-close")
    if ((closePanelBtn) && (to.path !== from.path)) {
        closePanelBtn.click();
    }
});

const app = Vue.createApp({
    components: {
        'app-header': Vue.defineAsyncComponent(() => loadModule('./components/app-header.vue', OPTIONS)),
        'app-sidepanel': Vue.defineAsyncComponent(() => loadModule('./components/app-sidepanel.vue', OPTIONS)),
        'app-footer': Vue.defineAsyncComponent(() => loadModule('./components/app-footer.vue', OPTIONS)),
    },
    data() {
        return {
            showHeader: false,
            currentPath: window.location.hash,
            notMainTemplate: FREE_ACCESS_PAGES,

            APP_TOKEN: APP_TOKEN,
            API_ROUTES: API_ROUTES
        }
    },
    mounted() {
        window.addEventListener('hashchange', () => {
            this.currentPath = window.location.hash
        })
    },
    methods: {
        clone(obj) {
            if (null == obj || "object" != typeof obj) return obj;
            var copy = obj.constructor();
            for (var attr in obj) {
                if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
            }
            return copy;
        }
    },
    watch: {
        '$route': function (value) {
            // lets watch for route changes on our
            // main parent app component.

            if (this.notMainTemplate.includes(value.name)) {
                this.showHeader = false
            } else {
                this.showHeader = true
            }
        }
    }
});
app.use(router)
app.use(store)
// app.use(VueToast.ToastPlugin);
app.use(ElementPlus)
// element-plus icons
for (const name in ElementPlusIconsVue) {
    app.component(name, ElementPlusIconsVue[name])
}
app.mount('#app');