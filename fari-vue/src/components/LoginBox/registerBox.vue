<template>
  <el-dialog
    :visible.sync="showRegisterBox"
    :before-close="cancel"
    :modal-append-to-body="false"
    title="Register"
    center
    style="border-radius: 10px;"
  >
    <el-form
      ref="registerForm"
      :rules="registerRules"
      :model="registerForm"
      :label-position="labelPosition"
    >
      <el-form-item
        label="Email: "
        prop="email"
      >
        <el-input
          v-model="registerForm.email"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item
        label="UserName: "
        prop="userName"
      >
        <el-input
          v-model="registerForm.userName"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item
        label="PassWord: "
        prop="passWord"
      >
        <el-input
          v-model="registerForm.passWord"
          type="password"
          autocomplete="off"
        />
      </el-form-item>
      <el-form-item
        label="Comnfirm PassWord: "
        prop="rePassWord"
      >
        <el-input
          v-model="registerForm.rePassWord"
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
        @click="startRegister"
      >
        Submit
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { register } from '@/api/user'
export default {
  name: 'RegisterBox',
  props: {
    showRegisterBox: {
      type: Boolean,
      default: false
    }
  },
  data () {
    var validateEmail = (rule, value, callback) => {
      if (!this.$options.methods.isEmail(value)) {
        callback(new Error('邮箱格式错误'))
      } else {
        callback()
      }
    }
    var validateRePasswd = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.registerForm.passWord) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      registerForm: {
        email: '',
        userName: '',
        passWord: '',
        rePassWord: ''
      },
      registerRules: {
        email: [
          { required: true, message: ' 请输入注册邮箱', trigger: 'blur' },
          { validator: validateEmail, trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, message: '用户名长度大于等于 4 个字符', trigger: 'blur' },
          { max: 12, message: '用户名长度不能大于 12 个字符', trigger: 'blur' }
        ],
        passWord: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 10, message: '密码长度需要大于等于 10 个字符', trigger: 'blur' },
          { max: 18, message: '密码长度不能大于 18 个字符', trigger: 'blur' }
        ],
        rePassWord: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { validator: validateRePasswd, trigger: 'blur' }
        ]
      },
      labelPosition: 'right'
    }
  },
  methods: {
    startRegister: function () {
      this.$refs.registerForm.validate((valid) => {
        if (!valid) {
          console.log('注册表单校验失败。')
        } else {
          var params = {}
          params.email = this.registerForm.email
          params.userName = this.registerForm.userName
          params.passWord = this.registerForm.passWord
          register(params).then(response => {
            if (response.code === this.$ECode.SUCCESS) {
              this.$message({
                type: 'success',
                message: 'register success!'
              })
              setTimeout(function () {
                location.replace(process.env.VUE_APP_WEB_API)
              }, 1500)
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
      this.$emit('register-cancel')
    },
    isEmail (s) {
      return /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(s)
    }
  }
}
</script>
