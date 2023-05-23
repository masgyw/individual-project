
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
            console.log('store login sucess called')
            if (payload) localStorage.setItem(APP_TOKEN, JSON.stringify(payload));
        },
        LOGIN_FAILLURE(state, payload) {
            state.isConnected, state.access_token = null;
            localStorage.removeItem(APP_TOKEN)
        }
    }
});