<template>
  <div class="login">
    <div class="login-form">
      <div class="login-form-title">frontend-ui</div>
      <div class="login-form-main">
        <el-form ref="loginForm" :model="form" :rules="rules">
          <el-form-item prop="username">
            <el-input v-model="form.username" clearable>
              <template #append>
                <el-icon>
                  <UserFilled />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="form.password" show-password clearable>
              <template #append>
                <el-icon>
                  <Lock />
                </el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%;" :loading="isLoading" @click="handleSubmit">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { Lock, UserFilled } from '@element-plus/icons'
import { user } from '@/api'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'Login',
  components: {
    Lock,
    UserFilled
  },
  setup () {
    const { dispatch } = useStore()
    const { push } = useRouter()
    const isLoading = ref(false)
    const form = reactive({ username: '', password: '' })
    const loginForm = ref(null)
    const rules = ref({
      username: [{ required: true, message: '请输入用户名', trigger: ['blur', 'change'] }],
      password: [{ required: true, message: '请输入密码', trigger: ['blur', 'change'] }]
    })
    const handleSubmit = () => {
      loginForm.value.validate(async valid => {
        if (!valid) return false
        isLoading.value = true
        const res = await user.login({
          ...form
        }).finally(() => { isLoading.value = false })
        if (res.code === 0) {
          dispatch('user/setLogin', res.result.token)
          push('/dashboard')
        }
      })
    }
    return {
      form,
      rules,
      loginForm,
      isLoading,
      handleSubmit
    }
  }
}
</script>

<style scoped lang="scss">
.login {
  background-image: url("../../assets/login-background.jpg");
  width: 100vw;
  height: 100vh;
  background-size: 100% 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  &-form {
    width: 600px;
    height: 350px;
    border-radius: 10px;
    background: #fff;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    &-title {
      width: 80%;
      margin: 15px 0;
      font-size: 20px;
      font-weight: bold;
    }
    &-main {
      display: flex;
      flex-direction: column;
      justify-content: center;
      width: 80%;
    }
  }
}
</style>
