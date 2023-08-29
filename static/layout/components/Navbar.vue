<template>
  <div class="navbar">
    <hamburger :is-active="sidebar.opened" class="hamburger-container" @toggleClick="toggleSideBar" />

    <breadcrumb class="breadcrumb-container" />

    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <img src="@/assets/avatar.gif" class="user-avatar">
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown" class="user-dropdown">
          <el-dropdown-item>
            <span style="display:block;" @click="logout">安全退出</span>
          </el-dropdown-item>
          <el-dropdown-item>
            <span style="display:block;" @click="modifyPass">修改密码</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-dialog :visible.sync="modifyForm" title="修改密码">
      <el-form ref="districtForm" :model="districtData" :rules="disRule" size="medium" label-width="100px">
        <el-form-item label="原来密码" prop="oldPass">
          <el-input
            v-model="districtData.oldPass"
            placeholder="请输入原来密码"
            clearable
            show-password
            :style="{width: '100%'}"
          />
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input
            v-model="districtData.newPass"
            placeholder="请输入新密码"
            clearable
            show-password
            :style="{width: '100%'}"
          />
        </el-form-item>
        <el-form-item label="确认新密码" prop="newPass2">
          <el-input
            v-model="districtData.newPass2"
            placeholder="请确认新密码"
            clearable
            show-password
            :style="{width: '100%'}"
          />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { modifyPass } from '@/api/sysuser/sysuser'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
export default {
  components: {
    Breadcrumb,
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar'
    ])
  },
  data() {
    return {
      modifyForm: false,
      districtData: {
        oldPass: '',
        newPass: '',
        newPass2: ''
      },
      disRule: {
        oldPass: [{
          required: true,
          message: '请输入原来密码',
          trigger: 'blur'
        }],
        newPass: [{
          required: true,
          message: '请输入新密码',
          trigger: 'blur'
        }],
        newPass2: [{
          required: true,
          message: '请确认新密码',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login`)
    },
    modifyPass() {
      this.modifyForm = true
    },
    handelConfirm() {
      this.$refs['districtForm'].validate(valid => {
        if (!valid) return
        modifyPass(JSON.stringify(this.districtData)).then(response => {
          this.$message.success({
            type: 'success',
            message: '修改成功'
          })
          this.modifyForm = false
        })
      })
    },
    close() {
      return false
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background .3s;
    -webkit-tap-highlight-color:transparent;

    &:hover {
      background: rgba(0, 0, 0, .025)
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}
</style>
