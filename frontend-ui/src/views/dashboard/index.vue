<template>
 <div class="dashboard-container">
  <div class="dashboard-text">管理中心欢迎您: {{ name }}</div>
 </div>
</template>

<script>
import { reactive, computed, toRefs } from 'vue'
import { useStore } from 'vuex'
export default {
  name: 'Home',
  setup () {
    const { state } = useStore()
    const params = reactive({
      name: computed(() => state.user.name)
    })
    return {
      ...toRefs(params)
    }
  }
}
</script>

<style scoped lang = "scss">
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
