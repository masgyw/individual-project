<template>
  <div class="common-layout">
    <el-container>
      <el-header><app-header v-if="$root.showHeader"></app-header></el-header>
      <el-container>
        <el-aside width="200px">
          <sidebar></sidebar>
        </el-aside>
        <el-container>
          <el-main>
            <el-scrollbar class="scrollbar-wrapper">
              <div class="app-main-section">
                <router-view :key="$route.path"></router-view>
              </div>
            </el-scrollbar>
          </el-main>
          <el-footer><app-footer v-if="$root.showHeader"></app-footer></el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

 
<script>
module.exports = {
  components: {
    "app-header": Vue.defineAsyncComponent(() =>
      loadModule("./components/app-header.vue", OPTIONS)
    ),
    "app-footer": Vue.defineAsyncComponent(() =>
      loadModule("./components/app-footer.vue", OPTIONS)
    ),
    "sidebar": Vue.defineAsyncComponent(() =>
      loadModule("./layout/sidebar.vue", OPTIONS)
    ),
  },
  data: function () {
    return {
      filter: 'world'
    }
  }
}
</script>
 
<style>
.hello {
  background-color: #ffe;
}
</style>