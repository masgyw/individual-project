// commonJS中，不能将import和module.exports混用，需用require和module.exports搭配使用
const { get } = require('js-cookie')
const tokens = {
  // 用户名和密码
  admin: {
    token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbl90eXBlIjoxLCJpZCI6Mn0.asMuppTXN33-i8_enktCyGlxuP7RrSjz9Pcf_GEFLP8',
    password: 'admin123'
  },
  editor: {
    token: 'editor-token',
    password: 'editor123'
  }
}

const users = {
  'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbl90eXBlIjoxLCJpZCI6Mn0.asMuppTXN33-i8_enktCyGlxuP7RrSjz9Pcf_GEFLP8': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: '超级管理员'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am a editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: '编辑者'
  }
}

module.exports = [
  // user login
  {
    url: '/frontend-ui/user/login',
    type: 'post',
    response: config => {
      const { userName, pwd } = config.body
      const token = tokens[userName]
      const password = tokens[userName]
      // mock error
      if (!token || !password || pwd !== password.password) {
        return {
          code: 60204,
          message: '账户名或密码错误'
        }
      }

      return {
        code: 20000,
        message: '登录成功',
        data: token
      }
    }
  },

  // get user info
  {
    url: '/frontend-ui/user/info.*',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]
      console.log('config', config)
      console.log('token', token)
      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/frontend-ui/user/logout',
    type: 'get',
    response: () => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  },

  // user roles
  {
    url: '/frontend-ui/user/getRoles',
    type: 'get',
    response: () => {
      const token = get('frontend-ui-token')
      const { roles } = users[token]
      return {
        code: 20000,
        data: roles,
        message: '获取成功'
      }
    }
  }
]
