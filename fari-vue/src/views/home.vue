<template>
  <div
    style="overflow:auto;"
  >
    <UserInfo :user-name="userName" />
    <el-row
      v-for="(blog, index) in blogs"
      :key="index"
    >
      <el-col
        :span="14"
        :offset="5"
      >
        <BlogCard
          :blog-info="blog"
        />
      </el-col>
    </el-row>
    <el-row>
      <el-pagination
        v-if="paginationFlag"
        layout="prev, pager, next"
        :page-count="pageNum"
        :page-size="pageSize"
        style="text-align:center;"
        hide-on-single-page
        @current-change="onPageIndexChange"
      />
    </el-row>
    <BackTop
      :parent-num="2"
    />
  </div>
</template>

<script>
import BlogCard from '@c/BlogCard/blogCard.vue'
import BackTop from '@c/FariFloatingBtn/backTop.vue'
import UserInfo from '@c/UserInfo/userInfo.vue'
import { getBlogsInfo, getPagesNum } from '@/api/blogs'
export default {
  name: 'Home',
  components: {
    BlogCard,
    BackTop,
    UserInfo
  },
  data () {
    return {
      blogs: [],
      pageSize: 8,
      pageIndex: 1,
      pageNum: 2,
      paginationFlag: false,
      userName: this.$route.params.user
    }
  },
  mounted: function () {
    this.init()
    this.paginationFlag = true
  },
  methods: {
    init () {
      this.getPages(this.pageSize)
      this.loadBlogs(this.pageSize, this.pageIndex)
    },
    getPages (pageSize) {
      getPagesNum(pageSize).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.pageNum = parseInt(response.data.pages)
          this.paginationFlag = true
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
        }
      })
    },
    loadBlogs (pageSize, pageIndex) {
      getBlogsInfo(pageSize, pageIndex).then(response => {
        if (response.success === true) {
          this.blogs = response.data.blogs
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
          setTimeout(function () {
            location.replace(process.env.VUE_APP_WEB_API + '/fariblog')
          }, 2000)
        }
      })
    },
    onPageIndexChange (currentIdx) {
      this.loadBlogs(this.pageSize, currentIdx)
    }
  }
}
</script>

<style>
  /* 使elementUI的分页器组件背景透明 */
  .el-pagination .number,
  .el-pagination button:disabled,
  .el-pagination .btn-next,
  .el-pagination .btn-prev {
    background:transparent !important;
  }
</style>
