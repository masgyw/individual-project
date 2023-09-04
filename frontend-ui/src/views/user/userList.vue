<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.phone" size="mini" placeholder="登录账号" />
        </el-col>
        <el-col :span="6">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">查找</el-button>
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="handleAdd">添加
          </el-button>
        </el-col>
      </el-row> <br>
    </div>
    <el-table
      :data="list"
      style="width: 100%"
    >
      <el-table-column label="id">
        <template v-slot="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="登录账号">
        <template v-slot="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="姓名">
        <template v-slot="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template v-slot="scope">
          <el-button
            v-if="scope.row.validStatus == 'INVALID'"
            size="small"
            type="text"
            @click="handleOnline(scope.row.id)"
          >启用</el-button>
          <el-button
            v-if="scope.row.validStatus == 'VALID'"
            size="small"
            type="text"
            @click="handleOffline(scope.row.id)"
          >禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column label="分配角色">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="primary"
            @click="showRoleTree(scope.row)"
          >分配角色</el-button>
        </template>
      </el-table-column>
      <el-table-column label="分配平台">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="primary"
            @click="showPlatTree(scope.row)"
          >分配平台</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      style="margin-top:15px"
      align="right"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :page-size="listQuery.limit"
      :total="total"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext"
    />

    <el-dialog :visible.sync="showTree" title="请选择给当前用户要授权的角色">
      <el-row>
        <el-form>
          <el-form-item>
            <el-button size="small" type="primary" style="margin-left: 28px;" @click="selectAll">全选</el-button>
            <el-button size="small" type="success" @click="unSelectAll">反选</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-tree
        ref="tree"
        :data="treeData"
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        empty-text="该平台下暂时没有角色"
        :default-checked-keys="selectRes"
        :props="rolePros"
      />
      <div slot="footer">
        <el-button @click="hiddenTree">取消</el-button>
        <el-button type="primary" @click="grant">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog v-model="showPlatformTree" title="请选择给当前用户要授权的平台">
      <el-row>
        <el-form>
          <el-form-item>
            <el-button size="small" type="primary" style="margin-left: 28px;" @click="selectAllPlat">全选</el-button>
            <el-button size="small" type="success" @click="unSelectAllPlat">反选</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-tree
        ref="ptree"
        :data="platformData"
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        empty-text="暂时没有平台"
        :default-checked-keys="selectPlatformRes"
        :props="platPros"
      />
      <div slot="footer">
        <el-button @click="hiddenPlatTree">取消</el-button>
        <el-button type="primary" @click="grantPlat">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog v-model="addForm" title="添加用户">
      <el-form ref="addForm" :model="addFormData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="姓名" prop="username">
          <el-input v-model="addFormData.username" placeholder="请输入姓名" clearable :style="{width: '100%'}" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="addFormData.phone" placeholder="请输入手机号" clearable :style="{width: '100%'}" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="addForm = false">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { findAllUsersByPage, getUserRoles, grantUserRoles, validUser, invalidUser, getUserPlatforms, grantUserPlatforms, create } from '../../api/sysuser/sysuser'

import { adminUser } from '@/api'

export default {
  data() {
    return {
      checkAll: false,
      grantUserId: '',
      showTree: false,
      platPros: {
        label: 'name'
      },
      appPros: {
        label: 'appName'
      },
      rolePros: {
        label: 'name'
      },
      addForm: false,
      showPlatformTree: false,
      showAppTree: false,
      selectRes: [],
      selectAppRes: [],
      selectPlatformRes: [],
      treeData: [],
      appData: [],
      platformData: [],
      listQuery: {
        page: 1,
        limit: 10,
        phone: ''
      },
      total: 0,
      list: null,
      listLoading: true,
      addFormData: {
        username: '',
        phone: ''
      },
      rules: {
        username: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        phone: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
          pattern: /^1\d{10}$/,
          message: '请输入正确的手机号',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      const queryData = {
        'bean': {
          'phone': this.listQuery.phone
        },
        'pageSize': this.listQuery.limit,
        'page': this.listQuery.page
      }
      adminUser.findAllUsersByPage(queryData).then(response => {
        this.list = response.result.records
        this.listLoading = false
        this.total = response.result.total
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
    handleOnline(id) {
      adminUser.validUser(id).then(response => {
        this.$message.success({
          message: '启用成功',
          type: 'success'
        })
        this.fetchData()
      })
    },
    handleOffline(id) {
      adminUser.invalidUser(id).then(response => {
        this.$message.success({
          message: '禁用成功',
          type: 'success'
        })
        this.fetchData()
      })
    },
    handelConfirm() {
      this.$refs['addForm'].validate(valid => {
        if (!valid) return
        adminUser.create(JSON.stringify(this.addFormData)).then(response => {
          this.$message.success({
            type: 'success',
            message: '保存成功'
          })
          this.addForm = false;
          this.fetchData()
        })
      })
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    handleAdd() {
      this.addForm = true
    },
    fetchPrev() {
      this.listQuery.page = this.listQuery.page - 1
      this.fetchData()
    },
    fetchPage(page) {
      this.listQuery.page = page
      this.fetchData()
    },
    changeSize(limit) {
      this.listQuery.limit = limit
      this.fetchData()
    },
    showRoleTree(row) {
      this.grantUserId = row.id
      adminUser.getUserRoles(row.id).then(response => {
        this.selectRes = response.result
        this.showTree = true
      })
    },
    showPlatTree(row) {
      this.grantUserId = row.id
      const request = {
        'code': '',
        'name': ''
      }
      adminUser.getUserPlatforms(row.id).then(response => {
        this.selectPlatformRes = response.result
        this.showPlatformTree = true
      })
    },
    hiddenTree() {
      this.showTree = false
    },
    hiddenPlatTree() {
      this.showPlatformTree = false
    },
    hiddenAppTree() {
      this.showAppTree = false
    },
    grant() {
      const request = {
        'roleIds': this.$refs.tree.getCheckedKeys(),
        'userId': this.grantUserId
      }
      adminUser.grantUserRoles(request).then(response => {
        this.$message.success({
          type: 'success',
          message: '授权成功'
        })
        this.showTree = false
      })
    },
    grantPlat() {
      const request = {
        'platformIds': this.$refs.ptree.getCheckedKeys(),
        'userId': this.grantUserId
      }
      adminUser.grantUserPlatforms(request).then(response => {
        this.$message.success({
          type: 'success',
          message: '授权成功'
        })
        this.showPlatformTree = false
      })
    },
    selectAll() {
      this.$refs.tree.setCheckedNodes(this.treeData)
    },
    unSelectAll() {
      this.$refs.tree.setCheckedKeys([])
    },
    selectAllPlat() {
      this.$refs.ptree.setCheckedNodes(this.platformData)
    },
    unSelectAllPlat() {
      this.$refs.ptree.setCheckedKeys([])
    },
    selectAllApp() {
      this.$refs.atree.setCheckedNodes(this.appData)
    },
    unSelectAllApp() {
      this.$refs.atree.setCheckedKeys([])
    }
  }
}
</script>
