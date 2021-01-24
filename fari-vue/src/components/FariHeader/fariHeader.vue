<template>
  <div>
    <div style="height:15px;" />
    <el-row>
      <el-col :span="4">
        <a :href="homePage"><i class="el-icon-caret-top"> {{ userName }}'s Blog</i></a>
      </el-col>
      <el-col
        :span="span"
        :offset="offset"
      >
        <a
          v-if="showAddBtn"
          class="spanBtn"
          style="margin-right:25px;cursor:pointer;"
          @click="loadEditor"
        ><i
          style="font-size:24px;font-weight:600;"
          class="el-icon-plus dyn"
        />
        </a>
        <a
          v-if="showEditBtn"
          style="margin-right:25px;cursor:pointer;"
          class="spanBtn"
          @click="editBlog"
        >
          <i
            style="font-size:24px;font-weight:600;"
            class="el-icon-edit dyn"
          />
        </a>
        <a
          v-if="showDeleteBtn"
          style="margin-right:25px;cursor:pointer;"
          class="spanBtn"
          @click="delBlog"
        >
          <i
            style="font-size:24px;font-weight:600;"
            class="el-icon-delete dyn"
          />
        </a>
        <a style="margin-right:20px;cursor:pointer;">
          <i
            style="font-size:24px;font-weight:600;"
            class="el-icon-moon dyn"
          />
        </a>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { deleteBlog } from '@/api/blogs'
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
      showAddBtn: true,
      showDeleteBtn: false,
      showEditBtn: false,
      shortOffset: 19,
      shortSpan: 1,
      mediumSpan: 2,
      mediumOffset: 18,
      longOffset: 16,
      longSpan: 4,
      span: 1,
      offset: 19
    }
  },
  watch: {
    userId (newValue, oldValue) {
      this.homePage = process.env.VUE_APP_WEB_API + `/user/${this.userName}/${this.userId}/home`
    },
    $route (toRoute, fromRoute) {
      this.renderMenu(toRoute.name)
    }
  },
  mounted () {
    this.renderMenu(this.$route.name)
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
    },
    delBlog () {
      var blogId = this.$route.params.blogId
      deleteBlog(blogId).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.$message({
            type: 'success',
            message: response.message
          })
          setTimeout(() => {
            this.$router.push({
              name: 'Home',
              params: {
                user: this.userName,
                userId: this.userId
              }
            })
          }, 2000)
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
        }
      })
    },
    editBlog () {
      var blogId = this.$route.params.blogId
      this.$router.push({
        name: 'Updator',
        params: {
          user: this.userName,
          userId: this.userId,
          blogId: blogId
        }
      })
    },
    renderMenu (toRoute) {
      if (toRoute === null) return
      if (toRoute === 'Home') {
        this.span = this.mediumSpan
        this.offset = this.mediumOffset
        this.showAddBtn = true
        this.showDeleteBtn = false
        this.showEditBtn = false
      } else if (toRoute === 'Blog') {
        this.span = this.longSpan
        this.offset = this.longOffset
        this.showDeleteBtn = true
        this.showEditBtn = true
        this.showAddBtn = true
      } else if (toRoute === 'Editor') {
        this.span = this.shortSpan
        this.offset = this.shortOffset
        this.showAddBtn = false
        this.showDeleteBtn = false
        this.showEditBtn = false
      }
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
  .spanBtn:hover{
    margin-right:19px !important;
    transition: margin-right 0.5s;
  }
  .spanBtn{
    margin-right:25px !important;
    transition: margin-right 0.3s;
  }
</style>
