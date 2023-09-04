<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.roleName" size="mini" placeholder="角色名称" />
        </el-col>
        <el-col :span="6">
          <el-select
            v-model="listQuery.platformId"
            placeholder="请选择所属平台"
            clearable
            :style="{width: '100%'}"
            size="mini"
          >
            <el-option
              v-for="(item, index) in platformIdOptions"
              :key="index"
              :label="item.name"
              :value="item.id"
              :disabled="item.disabled"
            />
          </el-select>
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
      <el-table-column label="角色名称">
        <template v-slot="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="角色编码">
        <template v-slot="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column label="所属平台" prop="platformId" :formatter="formatId" />
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.validStatus == 'INVALID'"
            size="mini"
            type="success"
            @click="handleOnline(scope.row.id)"
          >启用</el-button>
          <el-button
            v-if="scope.row.validStatus == 'VALID'"
            size="mini"
            type="danger"
            @click="handleOffline(scope.row.id)"
          >禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="warning"
            @click="grantResource(scope.row)"
          >分配资源</el-button>
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

    <el-dialog :visible.sync="addFormVisible" title="添加角色">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
          <el-col :span="20">
            <el-form-item label="角色编码" prop="code">
              <el-input v-model="formData.code" placeholder="请输入角色编码" clearable :style="{width: '100%'}" />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="角色名称" prop="name">
              <el-input v-model="formData.name" placeholder="请输入角色名称" clearable :style="{width: '100%'}" />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="所属平台" prop="platformId">
              <el-select
                v-model="formData.platformId"
                placeholder="请选择所属平台"
                clearable
                :style="{width: '100%'}"
              >
                <el-option
                  v-for="(item, index) in platformIdOptions"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                  :disabled="item.disabled"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="角色描述" prop="roleDesc">
              <el-input
                v-model="formData.roleDesc"
                type="textarea"
                placeholder="请输入角色描述"
                :autosize="{minRows: 4, maxRows: 4}"
                :style="{width: '100%'}"
              />
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="updateFormVisible" title="修改角色">
      <el-row :gutter="15">
        <el-form ref="updateForm" :model="updateFormData" :rules="rules" size="medium" label-width="100px">
          <el-col :span="20">
            <el-form-item label="角色编码" prop="code">
              <el-input v-model="updateFormData.code" placeholder="请输入角色编码" clearable :style="{width: '100%'}" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="角色名称" prop="name">
              <el-input v-model="updateFormData.name" placeholder="请输入角色名称" clearable :style="{width: '100%'}" />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="所属平台" prop="platformId">
              <el-select
                v-model="updateFormData.platformId"
                placeholder="请选择所属平台"
                clearable
                :style="{width: '100%'}"
                disabled
              >
                <el-option
                  v-for="(item, index) in platformIdOptions"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                  :disabled="item.disabled"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="角色描述" prop="roleDesc">
              <el-input
                v-model="updateFormData.roleDesc"
                type="textarea"
                placeholder="请输入角色描述"
                :autosize="{minRows: 4, maxRows: 4}"
                :style="{width: '100%'}"
              />
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="updateFormClose">取消</el-button>
        <el-button type="primary" @click="updateConfirm">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="showTree" title="请选择给当前角色要授权的资源">
      <el-tree
        ref="tree"
        :data="treeData"
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        :props="defaultProps"
        empty-text="该平台下暂时没有资源"
        :default-checked-keys="selectRes"
      />
      <div slot="footer">
        <el-button @click="hiddenTree">取消</el-button>
        <el-button type="primary" @click="grant">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { findRolesByPage, saveRole, invalidRole, validRole, updateRole, grantRoleResources, getRoleResourceIds } from '@/api/role'
// import { findValidPlatforms } from '../../api/platform/platform'
// import { getResourceTree } from '../../api/resource/resource'

import { resource,platform,role } from '@/api'

export default {
  components: {},
  inheritAttrs: false,
  props: [],
  data() {
    return {
      addFormVisible: false,
      updateFormVisible: false,
      showTree: false,
      treeData: [],
      selectRes: [],
      defaultProps: {
        label: 'name'
      },
      grantRoleId: '',
      listQuery: {
        page: 1,
        limit: 10,
        roleName: '',
        platformId: ''
      },
      total: 0,
      list: null,
      listLoading: true,
      formData: {
        code: '',
        name: '',
        platformId: '',
        roleDesc: ''
      },
      updateFormData: {
        id: '',
        code: '',
        name: '',
        platformId: '',
        roleDesc: ''
      },
      rules: {
        code: [{
          required: true,
          message: '请输入角色编码',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          message: '请输入角色名称',
          trigger: 'blur'
        }],
        platformId: [{
          required: true,
          message: '请选择所属平台',
          trigger: 'change'
        }],
        roleDesc: [{
          required: true,
          message: '请输入角色描述',
          trigger: 'blur'
        }]
      },
      platformIdOptions: []
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      const requestData =
        {
          'code': '',
          'name': ''
        }
      platform.findValidPlatforms(requestData).then(response => {
        this.platformIdOptions = response.result
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      const queryData = {
        'bean': {
          'roleName': this.listQuery.roleName,
          'platformId': this.listQuery.platformId
        },
        'size': this.listQuery.limit,
        'page': this.listQuery.page
      }
      role.findRolesByPage(queryData).then(response => {
        this.list = response.result.content
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
    handleUpdate(row) {
      this.updateFormData.id = row.id
      this.updateFormData.code = row.code
      this.updateFormData.platformId = row.platformId
      this.updateFormData.name = row.name
      this.updateFormData.roleDesc = row.roleDesc
      this.updateFormVisible = true
    },
    handleDetail(id) {
      this.$router.push({ path: '/monitor/detail', query: { id: id }})
    },
    handleOnline(id) {
      role.validRole(id).then(response => {
        this.$message.success({
          type: 'success',
          message: '启用成功'
        })
        this.fetchData()
      })
    },
    handleOffline(id) {
      role.invalidRole(id).then(response => {
        this.$message.success({
          type: 'success',
          message: '禁用成功'
        })
        this.fetchData()
      })
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    handleAdd() {
      this.addFormVisible = true
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
    close() {
      this.addFormVisible = false
    },
    updateFormClose() {
      this.updateFormVisible = false
    },
    handelConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        const requestData =
          {
            'code': this.formData.code,
            'name': this.formData.name,
            'platformId': this.formData.platformId,
            'roleDesc': this.formData.roleDesc
          }
        role.saveRole(requestData).then(response => {
          this.$message.success({
            type: 'success',
            message: '保存成功'
          })
          this.addFormVisible = false
          this.fetchData()
        })
        this.close()
      })
    },
    updateConfirm() {
      this.$refs['updateForm'].validate(valid => {
        if (!valid) return
        const requestData =
          {
            'name': this.updateFormData.name,
            'roleDesc': this.updateFormData.roleDesc,
            'id': this.updateFormData.id
          }
        role.updateRole(requestData).then(response => {
          this.$message.success({
            type: 'success',
            message: '更新成功'
          })
          this.updateFormVisible = false
          this.fetchData()
        })
        this.close()
      })
    },
    formatId(row, column) {
      const pid = row.platformId
      const match = this.platformIdOptions.filter(s => s.id === pid)
      return match[0].name
    },
    grantResource(row) {
      resource.getResourceTree(row.platformId).then(response => {
        this.treeData = response.result
        this.grantRoleId = row.id
      })
      role.getRoleResourceIds(row.id).then(response => {
        this.selectRes = response.result
        this.showTree = true
      })
    },
    grant() {
      const request = {
        'resourceIds': this.$refs.tree.getCheckedKeys(),
        'roleId': this.grantRoleId
      }
      role.grantRoleResources(request).then(response => {
        this.$message.success({
          type: 'success',
          message: '授权成功'
        })
        this.showTree = false
      })
    },
    hiddenTree() {
      this.showTree = false
    }
  }
}
</script>
