<template>
  <el-dialog
    :visible.sync="showLoignBox"
    :before-close="cancel"
    :modal-append-to-body="false"
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
import { setCookie } from '@/utils/cookieUtils'
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
          { min: 4, message: '用户名长度大于等于 4 个字符', trigger: 'blur' },
          { max: 12, message: '用户名长度不能大于 12 个字符', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 10, message: '密码长度需要大于等于 10 个字符', trigger: 'blur' },
          { max: 18, message: '密码长度不能大于 18 个字符', trigger: 'blur' }
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
            if (response.code === this.$ECode.SUCCESS) {
              this.$message({
                type: 'success',
                message: response.message
              })
              setCookie('access_token', response.data.access_token)
              setCookie('refresh_token', response.data.refresh_token)
              setTimeout(function () {
                location.replace(process.env.VUE_APP_WEB_API + `/index/${response.data.userId}`)
              }, 2000)
            } else {
              this.$message({
                type: 'error',
                message: response.message
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
