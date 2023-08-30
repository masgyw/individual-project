<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.code" size="mini" placeholder="资源编码" />
        </el-col>
        <el-col :span="6">
          <el-input v-model="listQuery.name" size="mini" placeholder="资源名称" />
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
    <el-table-column label="资源名称" key="slot">
        <template #default="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="所属平台" prop="platformId" :formatter="formatId" />
      <el-table-column label="资源编码" key="slot">
        <template #default="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column label="url" key="slot">
        <template #default="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>
      <el-table-column label="路由" key="slot">
        <template #default="scope">
          {{ scope.row.router }}
        </template>
      </el-table-column>
      <el-table-column label="状态" key="slot">
        <template #default="scope">
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
      <el-table-column label="操作" key="slot">
        <template #default="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 官网推荐使用v-model 双向绑定 -->
    <el-pagination
      style="margin-top:15px"
      align="center"
      background
      layout="total, sizes, prev, pager, next, jumper"
      v-model:page-size="listQuery.limit"
      :total="total"
      @current-change="fetchPage"
      @prev-click="fetchPrev"
      @next-click="fetchNext"
    />
  </div>
</template>

<script>

import { resource } from '@/api'
// import { findValidPlatforms } from '../../api/platform/platform'

export default {
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 10,
        name: '',
        code: '',
        platformId: ''
      },
      total: 0,
      list: null,
      listLoading: true,
      platformIdOptions: []
    }
  },
  created() {
    // this.init()
    this.fetchData()
  },
  methods: {
    init() {
      const requestData =
        {
          'code': '',
          'name': ''
        }
      findValidPlatforms(requestData).then(response => {
        this.platformIdOptions = response.result
      })
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      const queryData = {
        'bean': {
          'name': this.listQuery.name,
          'code': this.listQuery.code,
          'platformId': this.listQuery.platformId
        },
        'pageSize': this.listQuery.limit,
        'page': this.listQuery.page
      }
      resource.findByPage(queryData).then(response => {
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
      this.getList()
    },
    handleUpdate(row) {
      this.$router.push({ path: '/permission/update', query: { row: row }})
    },
    handleDetail(id) {
      this.$router.push({ path: '/resource/detail', query: { id: id }})
    },
    handleOnline(id) {

    },
    handleOffline(id) {
      console.log('dd')
    },
    fetchNext() {
      this.listQuery.page = this.listQuery.page + 1
      this.fetchData()
    },
    handleAdd() {
      this.$router.push({ path: '/permission/add' })
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
    formatId(row, column) {
      const pid = row.platformId
      const match = this.platformIdOptions.filter(s => s.id === pid)
      return match[0].name
    }
  }
}
</script>
