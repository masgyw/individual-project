<template>
  <div>
    {{ userName }}: 你好 vue3
    <el-button>ok</el-button>
    <div>
      <!-- <addressComponent></addressComponent> -->
    </div>
    <!-- <div>{{ help }}</div> -->
  </div>
</template>

<script>
// import mjs
// import { help } from './assets/mjs/help.mjs'
// 必须添加 .vue 后缀
// import addressComponent from "./views/addressComponent.vue"

import { request } from './assets/js/axios-1.2.2.min.js'

import { getCurrentInstance } from "vue";

export default {
  name: "index",
  // components: { addressComponent },
  data() {
    return {
      userName: "张三",
      // help
    }
  },
  setup() {
    // const axios = require('axios');

    // const { proxy } = getCurrentInstance();
    // console.log(proxy)
    // axios.get("https://img-home.csdnimg.cn/data_json/toolbar/toolbar0812.json")
    // .then((response) => {
    //   console.log(response);
    // });
  },
  created() {
    // console.log(help)
  },
  mounted() {
    axios.get("https://img-home.csdnimg.cn/data_json/toolbar/toolbar0812.json")
      .then((response) => {
        console.log(response);
      }).catch((err) => {
        console.log(err)
      });
  },
  methods: {}
}
</script>