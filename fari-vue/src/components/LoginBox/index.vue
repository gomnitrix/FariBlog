<template>
  <el-dialog
    :visible.sync="showLoignBox"
    :before-close="cancel"
    title="Login"
    center
  >
    <el-form
      ref="loginForm"
      :rules="loginRules"
      :model="loginForm"
      :label-position="labelPosition"
    >
      <el-form-item
        label="UserName"
        prop="userName"
      >
        <el-input
          v-model="loginForm.userName"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item
        label="PassWord"
        prop="passWord"
      >
        <el-input
          v-model="loginForm.passWord"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
    </el-form>
    <div
      slot="footer"
      class="dialog-footer"
    >
      <el-button
        type="primary"
        plain
        style="width:100px"
        @click="cancel"
      >
        Cancel
      </el-button>
      <el-button
        type="success"
        plain
        style="width:100px"
        @click="startLogin"
      >
        Submit
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { login } from '@/api/user'
export default {
  name: 'Login',
  props: {
    showLoignBox: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      loginForm: {
        userName: '',
        passWord: ''
      },
      loginRules: {
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, message: '用户名长度大于等于 5 个字符', trigger: 'blur' },
          { max: 20, message: '用户名长度不能大于 20 个字符', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, message: '密码长度需要大于等于 5 个字符', trigger: 'blur' },
          { max: 20, message: '密码长度不能大于 20 个字符', trigger: 'blur' }
        ]
      },
      labelPosition: 'right'
    }
  },
  methods: {
    startLogin: function () {
      this.$refs.loginForm.validate((valid) => {
        if (!valid) {
          console.log('登录表单校验失败。')
        } else {
          var params = {}
          params.userName = this.loginForm.userName
          params.passWord = this.loginForm.passWord
          // params.isRememberMe = 1
          login(params).then(response => {
            console.log('hello there!')
            console.log(this.$ECode.SUCCESS)
            console.log('done')
            if (response.code === this.$ECode.SUCCESS) {
              console.log('success!: ')
              console.log(response.data)
              location.replace(process.env.VUE_APP_WEB_API + '/?userId=' + response.data)
              window.location.reload()
            } else {
              this.$message({
                type: 'error',
                message: response.data
              })
            }
          })
        }
      }
      )
    },
    cancel () {
      this.$emit('login-cancel')
    }
  }
}
</script>
