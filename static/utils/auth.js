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
    console.log("token=", app_token)
    if (app_token) app_token = JSON.parse(app_token) // Parse the json string value

    var options = {
        headers: {
            'Authorization': `Bearer ${(app_token ? app_token.token : '')}`
        }
    }

    var mustRefresh = null;
    if (app_token) {
        // const canAccess = await axios.get(API_ROUTES.me, options)
        //     .catch((error) => {
        //         let message = typeof error.response !== 'undefined' ? error.response.data.message : error.message;
        //         // localStorage.removeItem(APP_TOKEN)
        //         // store.commit('LOGIN_FAILLURE');
        //         // next({ name: 'login' });
        //         mustRefresh = true;
        //     }).finally(() => {
        //         if (mustRefresh) {
        //             // Refresh token
        //             return axios.post(API_ROUTES.refresh, { "refresh_token": app_token.refresh_token })
        //                 .then((result) => {
        //                     store.commit('LOGIN_SUCCESS', result.data);
        //                     next({ name: to.name });
        //                 })
        //                 .catch((error) => {
        //                     let message = typeof error.response !== 'undefined' ? error.response.data.message : error.message;
        //                     localStorage.removeItem(APP_TOKEN);
        //                     store.commit('LOGIN_FAILLURE');
        //                     next({ name: 'login' });
        //                 })
        //         }
        //     })

        // if (
        //     (typeof canAccess.data !== 'undefined')
        //     &&
        //     (to.name !== 'login')
        // ) {
        //     store.commit('LOGIN_SUCCESS', app_token);
        //     next()
        // } else next({ name: 'login' });

        store.commit('LOGIN_SUCCESS', app_token);
        next()
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