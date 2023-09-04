<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.name" size="mini" placeholder="平台名称" />
        </el-col>
        <el-col :span="6">
          <el-button type="success" size="mini" icon="el-icon-search" @click.native="search">查找</el-button>
          <el-button type="success" size="mini" icon="el-icon-plus" @click.native="handleAdd">添加
          </el-button>
        </el-col>
      </el-row> <br>
    </div>
    <el-table :data="list" style="width: 100%">
      <el-table-column label="平台名称">
        <template v-slot="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="平台编码">
        <template v-slot="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template v-slot="scope">
          <el-button v-if="scope.row.validStatus == 'INVALID'" size="mini" type="success"
            @click="handleOnline(scope.row.id)">启用</el-button>
          <el-button v-if="scope.row.validStatus == 'VALID'" size="mini" type="danger"
            @click="handleOffline(scope.row.id)">禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button size="mini" type="primary" @click="handleUpdate(scope.row)">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="margin-top:15px" align="right" background layout="total, sizes, prev, pager, next, jumper"
      :page-size="listQuery.limit" :total="total" @current-change="fetchPage" @prev-click="fetchPrev"
      @next-click="fetchNext" />

    <el-dialog v-model="addFormVisible" title="添加平台">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
          <el-col :span="20">
            <el-form-item label="平台名称" prop="code">
              <el-input v-model="formData.name" placeholder="请输入平台名称" clearable :style="{ width: '100%' }" />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="平台编码" prop="name">
              <el-input v-model="formData.code" placeholder="请输入平台编码" clearable :style="{ width: '100%' }" />
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog v-model="updateFormVisible" title="修改平台">
      <el-row :gutter="15">
        <el-form ref="updateForm" :model="updateFormData" :rules="rules" size="medium" label-width="100px">
          <el-col :span="20">
            <el-form-item label="平台名称" prop="code">
              <el-input v-model="updateFormData.name" placeholder="请输入平台名称" clearable :style="{ width: '100%' }" />
            </el-form-item>
          </el-col>
          <el-col :span="20">
            <el-form-item label="平台编码" prop="name">
              <el-input v-model="updateFormData.code" placeholder="请输入平台编码" clearable :style="{ width: '100%' }" />
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="updateFormClose">取消</el-button>
        <el-button type="primary" @click="updateConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { findPlatformByPage, savePlatform, updatePlatform, validPlatform, invalidPlatform } from '../../api/platform/platform'

import { platform } from '@/api'

export default {
  components: {},
  inheritAttrs: false,
  props: [],
  data() {
    return {
      addFormVisible: false,
      updateFormVisible: false,
      grantRoleId: '',
      listQuery: {
        page: 1,
        limit: 10,
        name: ''
      },
      total: 0,
      list: null,
      listLoading: true,
      formData: {
        code: '',
        name: ''
      },
      updateFormData: {
        id: '',
        code: '',
        name: ''
      },
      rules: {
        code: [{
          required: true,
          message: '请输入平台编码',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          message: '请输入平台名称',
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
          'name': this.listQuery.name
        },
        'pageSize': this.listQuery.limit,
        'page': this.listQuery.page
      }
      platform.findPlatformByPage(queryData).then(response => {
        this.list = response.result.records
        this.listLoading = false
        this.total = parseInt(response.result.total)
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
      this.updateFormData.name = row.name
      this.updateFormVisible = true
    },
    handleOnline(id) {
      platform.validPlatform(id).then(response => {
        this.$message.success({
          type: 'success',
          message: '启用成功'
        })
        this.fetchData()
      })
    },
    handleOffline(id) {
      platform.invalidPlatform(id).then(response => {
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
      console.log("handle add button")
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
          'name': this.formData.name
        }
        platform.savePlatform(requestData).then(response => {
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
          'code': this.updateFormData.code,
          'id': this.updateFormData.id
        }
        platform.updatePlatform(requestData).then(response => {
          this.$message.success({
            type: 'success',
            message: '更新成功'
          })
          this.updateFormVisible = false
          this.fetchData()
        })
        this.close()
      })
    }
  }
}
</script>
