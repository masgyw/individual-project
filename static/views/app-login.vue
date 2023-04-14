<template>
  <div class="hello">登录页面 {{ who }}

    <el-button>确认</el-button>
  </div>
</template>
 
<script>
module.exports = {
  data: function () {
    return {
      errors: [],
      form: { username: "", pass: "", remember: false },
    }
  },
  components: {
    "app-footer": Vue.defineAsyncComponent(() =>
      loadModule("./components/app-footer.vue", OPTIONS)
    ),
  },
  methods: {
    signinUser() {
      let app = this;
      axios
        .post(API_ROUTES.auth, {
          username: this.form.username,
          password: this.form.pass,
        })
        .then((result) => {
          if (result.data.token) {
            app.$store.commit("LOGIN_SUCCESS", result.data);
            app.$router.push("/");
          }
        })
        .catch((error) => {
          let message =
            typeof error.response !== "undefined"
              ? error.response.data.message
              : error.message;
          console.log('login error :', message)
        });
    },
  },
  mounted() {
    this.$store.commit("LOGIN_SUCCESS", "123456");
    this.$router.push("/");
  }
}
</script>
 
<style>
.hello {
  background-color: #ffe;
}
</style>