<template>
  <div class="aside">
    <el-scrollbar class="scrollbar-wrapper">
      <el-menu router :default-active="nowActive" class="el-menu-vertical-demo">
        <el-menu-item v-for="item in filterRouter" :key="item.path" :item="item" :index="item.path">
          <el-icon><Document /></el-icon>
          <template #title>
            <span>{{ item.meta.title }}</span>
          </template>
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
  }
}
</script>
