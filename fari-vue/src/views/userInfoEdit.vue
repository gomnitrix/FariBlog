<template>
  <div id="box-wrapper">
    <el-card class="info-box">
      <div id="img-wrapper">
        <img
          id="avatar"
          :src="avatarSrc"
        >
      </div>
      <div style="margin-top: 40px;font-size: 1.45rem;">
        <h1 style="margin-bottom: 10px;">
          {{ userInfo.userName }}
        </h1>
      </div>
      <div> {{ userInfo.summary }} </div>
      <div style="margin-top: 25px; margin-bottom: 30px;">
        <el-row class="editor-row">
          <el-col :span="labelSpan">
            <label v-html="labels.userName" />
          </el-col>
          <el-col
            :offset="labelSpan"
            :span="inputSpan"
          >
            <el-input
              v-model="userInfo.userName"
              class="input-area"
              :disabled="editFlags.userName"
            />
          </el-col>
          <el-col :span="btnSpan">
            <i
              class="el-icon-edit edit-btn"
              @click="editUserName"
            />
          </el-col>
        </el-row>

        <el-row class="editor-row">
          <el-col :span="labelSpan">
            <label v-html="labels.summary" />
          </el-col>
          <el-col
            :offset="labelSpan"
            :span="inputSpan"
          >
            <el-input
              v-model="userInfo.summary"
              class="input-area"
              type="textarea"
              autosize
              :disabled="editFlags.summary"
            />
          </el-col>
          <el-col :span="btnSpan">
            <i
              class="el-icon-edit edit-btn"
              @click="editSummary"
            />
          </el-col>
        </el-row>

        <el-row class="editor-row">
          <el-col :span="labelSpan">
            <label v-html="labels.email" />
          </el-col>
          <el-col
            :offset="labelSpan"
            :span="inputSpan"
          >
            <div style="display:flex;">
              <el-input
                v-model="userInfo.email.prefix"
                class="input-area"
                :disabled="editFlags.email"
              />
              <el-autocomplete
                v-model="userInfo.email.suffix"
                :disabled="editFlags.email"
                :fetch-suggestions="querySearch"
                :trigger-on-focus="true"
                @select="handleSelect"
              >
                <template slot="prepend">
                  @
                </template>
              </el-autocomplete>
            </div>
          </el-col>
          <el-col :span="btnSpan">
            <i
              class="el-icon-edit edit-btn"
              @click="editEmail"
            />
          </el-col>
        </el-row>

        <el-row class="editor-row">
          <el-col :span="labelSpan">
            <label v-html="labels.passWord" />
          </el-col>
          <el-col
            :offset="labelSpan"
            :span="inputSpan"
          >
            <el-input
              v-model="userInfo.passWord"
              class="input-area"
              show-password
              :disabled="editFlags.passWord"
            />
          </el-col>
          <el-col :span="btnSpan">
            <i
              class="el-icon-edit edit-btn"
              @click="editPassWord"
            />
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { updateUser } from '@/api/user'
export default {
  name: 'UserInfo',
  data () {
    return {
      inputSpan: 22,
      labelSpan: 1,
      btnSpan: 1,
      avatarSrc: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      userInfo: {
        userName: 'FariUser',
        summary: 'Hi! There is empty...',
        passWord: '',
        email: {
          prefix: 'email',
          suffix: 'example.com'
        }
      },
      editFlags: {
        userName: true,
        summary: true,
        passWord: true,
        email: true
      },
      labels: {
        userName: 'UserName:&emsp;&emsp;',
        summary: 'Summary:&emsp;&emsp;',
        email: 'Email:&emsp;&emsp;',
        passWord: 'PassWord:&emsp;&emsp;'
      },
      email_suffixs: []
    }
  },
  mounted () {
    this.email_suffixs = this.loadAll()
  },
  methods: {
    errorHandler () {
      this.avatarSrc = 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
    },
    querySearch (queryString, cb) {
      console.log(queryString)
      var emails = this.email_suffixs
      var results = queryString ? emails.filter(this.createFilter(queryString)) : emails
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter (queryString) {
      return (email) => {
        return (email.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    handleSelect (item) {
      console.log(item)
    },
    loadAll () {
      return [
        { value: 'qq.com' },
        { value: 'gmail.com' },
        { value: 'outlook.com' },
        { value: '163.com' }
      ]
    },
    editSummary () {
      this.editFlags.summary = false
      this.userInfo.summary = ''
    },
    editEmail () {
      this.editFlags.email = false
      this.userInfo.email = ''
    },
    editPassWord () {
      this.editFlags.passWord = false
      this.userInfo.passWord = ''
    },
    editUserName () {
      this.editFlags.userName = false
      this.userInfo.userName = ''
    },
    update () {
      var params = this.userInfo
      params.uid = this.$route.params.userId
      updateUser(params).then(response => {
        if (response.success === true) {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.$router.push({
            name: 'Home',
            params: {
              user: this.userInfo.userName,
              userId: params.uid
            }
          })
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
        }
      })
    }
  }

}
</script>

<style scoped>
    #box-wrapper{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 95%;
    }
    .info-box{
        position: fixed;
        text-align: center;
        margin-top: -2rem;
        border-radius: .5rem;
        width:1110px;
        box-shadow: 0 12px 15px 0 rgba(0,0,0,.24), 0 17px 50px 0 rgba(0,0,0,.19) !important;
    }
    #img-wrapper{
      margin: -8rem auto 8rem -10rem;
      display: inline-block;
      height: 100%;
    }
    #avatar{
        border-radius: 50%;
        box-shadow: 0 2px 5px 0 rgba(0,0,0,.16), 0 2px 10px 0 rgba(0,0,0,.12);
        display: block;
        position: fixed;
        width: 10rem;
        height: 10rem;
    }
    .editor-row{
      display: flex;
      align-items: center;
      margin-bottom: 20px;
    }
    .edit-btn{
      font-size: 1.5rem;
      color: #000000;
      transition: color 0.3s;
      transition: font-size 0.3s;
    }
    .edit-btn:hover{
      color: #30a9de;
      font-size: 1.495rem;
      cursor:pointer;
      transition: color 0.3s;
      transition: font-size 0.3s;
    }

</style>
