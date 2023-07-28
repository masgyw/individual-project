<template>
  <div class="app-container">
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="资源名称" prop="name">
            <el-input
              v-model="formData.name"
              placeholder="请输入资源名称"
              :maxlength="50"
              clearable
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="资源编码" prop="code">
            <el-input
              v-model="formData.code"
              placeholder="请输入资源编码"
              :maxlength="50"
              clearable
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="icon-class" prop="icon">
            <el-input v-model="formData.icon" placeholder="请输入icon-class" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="前端路由" prop="router">
            <el-input v-model="formData.router" placeholder="请输入前端路由" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="url" prop="url">
            <el-input v-model="formData.url" placeholder="请输入url" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="排序号" prop="sortNum">
            <el-input v-model="formData.sortNum" placeholder="请输入排序号" clearable :style="{width: '100%'}" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="所属平台" prop="platform">
            <el-select v-model="formData.platform" placeholder="请选择所属平台" clearable :style="{width: '100%'}">
              <el-option
                v-for="(item, index) in platformOptions"
                :key="index"
                :label="item.name"
                :value="item.id"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="父级菜单" prop="pid">
            <el-input v-model="formData.pidName" placeholder="请输入父级菜单" clearable :style="{width: '100%'}" />
            <el-input v-model="formData.pid" clearable :style="{width: '100%'}" type="hidden" />
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-button type="primary" @click="selectPid">选择父级菜单</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="资源类型" prop="sourceType">
            <el-select v-model="formData.sourceType" placeholder="请选择资源类型" clearable :style="{width: '100%'}">
              <el-option
                v-for="(item, index) in sourceTypeOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="资源描述" prop="resourceDesc">
            <el-input
              v-model="formData.resourceDesc"
              type="textarea"
              placeholder="请输入资源描述"
              :autosize="{minRows: 4, maxRows: 4}"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

    <el-dialog :visible.sync="showTree" title="请选择父级菜单">
      <el-tree
        ref="tree"
        :data="treeData"
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        :props="defaultProps"
        empty-text="该平台下暂时没有资源"
        check-strictly="true"
        @check-change="handleClick"
        @node-click="nodeClick"
      />
      <div slot="footer">
        <el-button @click="hiddenTree">取消</el-button>
        <el-button type="primary" @click="selectNode">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>

import { updateResource } from '../../api/resource/resource'
import { findValidPlatforms } from '../../api/platform/platform'
import { getResourceTree } from '../../api/resource/resource'

export default {
  components: {},
  props: [],
  data() {
    return {
      i: 0,
      showTree: false,
      treeData: [],
      defaultProps: {
        label: 'name'
      },
      formData: {
        id: '',
        name: '',
        code: '',
        icon: '',
        router: '',
        url: '',
        sortNum: '',
        pid: '',
        pidName: '',
        platform: '',
        sourceType: '',
        resourceDesc: ''
      },
      rules: {
        name: [{
          required: true,
          message: '请输入资源名称',
          trigger: 'blur'
        }],
        code: [{
          required: true,
          message: '请输入资源编码',
          trigger: 'blur'
        }],
        icon: [{
          required: true,
          message: '请输入icon-class',
          trigger: 'blur'
        }],
        router: [],
        url: [],
        sortNum: [{
          required: true,
          message: '请输入排序号',
          trigger: 'blur'
        }],
        pid: [{
          required: true,
          message: '请输入父级菜单',
          trigger: 'blur'
        }],
        platform: [{
          required: true,
          message: '请选择所属平台',
          trigger: 'change'
        }],
        sourceType: [{
          required: true,
          message: '请选择资源类型',
          trigger: 'change'
        }],
        resourceDesc: [{
          required: true,
          message: '请输入资源描述',
          trigger: 'blur'
        }]
      },
      platformOptions: [],
      sourceTypeOptions: [{
        'label': '模块',
        'value': 'MODULE'
      }, {
        'label': '菜单',
        'value': 'MENU'
      }, {
        'label': '按钮或者链接',
        'value': 'FUNC'
      }]
    }
  },
  computed: {},
  watch: {},
  created() {
    this.init()
  },
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        const createRequest =
            {
              'code': this.formData.code,
              'iconClass': this.formData.icon,
              'name': this.formData.name,
              'pid': this.formData.pid,
              'platformId': this.formData.platform,
              'resourceDesc': this.formData.resourceDesc,
              'router': this.formData.router,
              'sortNum': this.formData.sortNum,
              'resourceType': this.formData.sourceType,
              'url': this.formData.url,
              'id': this.formData.id
            }
        updateResource(createRequest).then(response => {
          this.$router.push({ path: '/permission/list' })
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    init() {
      const requestData =
          {
            'code': '',
            'name': ''
          }
      findValidPlatforms(requestData).then(response => {
        this.platformOptions = response.result
      })
      const row = this.$route.query.row
      this.formData.id = row.id
      this.formData.name = row.name
      this.formData.code = row.code
      this.formData.icon = row.iconClass
      this.formData.router = row.router
      this.formData.url = row.url
      this.formData.sortNum = row.sortNum
      this.formData.pid = row.pid
      this.formData.pidName = row.pidName
      this.formData.platform = row.platformId
      this.formData.sourceType = row.resourceType
      this.formData.resourceDesc = row.resourceDesc
    },
    selectPid() {
      if (this.formData.platform === '') {
        this.$message.info({
          type: 'info',
          message: '请先选择平台'
        })
        return
      }
      getResourceTree(this.formData.platform).then(response => {
        this.treeData = response.result
        this.showTree = true
      })
    },
    hiddenTree() {
      this.showTree = false
    },
    selectNode() {
      this.showTree = false
    },
    handleClick(data, checked, node) {
      if (checked === true) {
        this.formData.pid = data.id
        this.formData.pidName = data.name
        this.$refs.tree.setCheckedNodes([data])
      }
    },
    nodeClick(data, checked, node) {
      this.formData.pid = data.id
      this.formData.pidName = data.name
      this.$refs.tree.setCheckedNodes([data])
    }
  }
}

</script>
<style>
</style>
