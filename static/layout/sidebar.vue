<template>
  <div class="aside">
    <el-scrollbar class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :unique-opened="false"
        :collapse-transition="false"
        mode="vertical"
      >
        <el-menu-item v-for="item in routes" :key="item.path" :item="item" :index="item.path">
          <el-icon><Document /></el-icon>

            <span>{{ item.meta.title }}</span>

        </el-menu-item>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
module.exports = {
  components: {
  },
  data: function () {
    return {
      nowActive: '',
      filterRouter: [
        {path: '/', meta: { icon: '', title: 'Dashboard' }},
        {path: '/house', meta: { icon: '', title: '房屋数据' }},
        {path: '/data-file', meta: { icon: '', title: '数据文件' }},
      ]
    }
  },
  computed: {
    routes() {
      console.log("", this.$router.options.routes)
      return this.$router.options.routes
    },
    activeMenu() {
      const route = this.$route
      const { meta, path } = route
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu
      }
      return path
    },
    isCollapse() {
      return false;
    }
  }
}
</script>

<style>
.aside {
  height: 100%;
}
</style>