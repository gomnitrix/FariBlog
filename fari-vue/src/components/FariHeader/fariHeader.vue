<template>
  <div>
    <div style="height:15px;" />
    <el-row>
      <el-col :span="2">
        <a :href="homePage"><i class="el-icon-caret-top"> {{ userName }}'s Blog</i></a>
      </el-col>
      <el-col
        :span="1"
        :offset="21"
      >
        <a
          id="newBlogBtn"
          style="margin-right:25px;cursor:pointer;"
          @click="loadEditor"
        ><i
          style="font-size:24px;font-weight:600;"
          class="el-icon-plus dyn"
        /></a>
        <a style="cursor:pointer;">
          <i
            style="font-size:24px;font-weight:600;"
            class="el-icon-moon dyn"
          /></a>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  name: 'FariHeader',
  props: {
    userName: {
      type: String,
      default: ''
    },
    userId: {
      type: String,
      default: '0'
    }
  },
  data () {
    return {
      homePage: '',
      editorPage: ''
    }
  },
  watch: {
    userId (newValue, oldValue) {
      this.editorPage = process.env.VUE_APP_WEB_API + `/user/${this.userId}/editor`
      this.homePage = process.env.VUE_APP_WEB_API + `/user/${this.userId}/home`
    }
  },
  methods: {
    loadEditor () {
      this.$router.push({
        name: 'Editor',
        params: {
          user: this.userName,
          userId: this.userId
        }
      })
    //   location.replace(this.editorPage)
    }
  }
}
</script>

<style scoped>
  .dyn:hover{
    font-size:30px !important;
    transition: font-size 0.5s;
  }
  .dyn{
    font-size:24px !important;
    transition: font-size 0.3s;
  }
  #newBlogBtn:hover{
    margin-right:19px !important;
    transition: margin-right 0.5s;
  }
  #newBlogBtn{
    margin-right:25px !important;
    transition: margin-right 0.3s;
  }
</style>
