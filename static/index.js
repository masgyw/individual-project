const { loadModule } = window['vue3-sfc-loader'];
// End vue3-sfc-loader

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