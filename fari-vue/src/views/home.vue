<template>
  <div>
    <el-row
      v-for="(blog, index) in blogs"
      :key="index"
    >
      <el-col
        :span="8"
        :offset="8"
      >
        <BlogCard
          :blog-info="blog"
        />
      </el-col>
    </el-row>
    <el-row>
      <el-pagination
        layout="prev, pager, next"
        :total="pageNum"
        style="text-align:center;"
        hide-on-single-page
      />
    </el-row>
    <BackTop />
  </div>
</template>

<script>
import BlogCard from '@c/BlogCard/blogCard.vue'
import BackTop from '@c/FariFloatingBtn/backTop.vue'
import { getBlogsInfo, getPagesNum } from '@/api/blogs'
export default {
  name: 'Home',
  components: {
    BlogCard,
    BackTop
  },
  data () {
    return {
      blogs: [],
      pageSize: 8,
      pageIndex: 1,
      pageNum: 1
    }
  },
  mounted: function () {
    this.loadBlogs()
  },
  methods: {
    getPages () {
      getPagesNum(this.pageSize).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.pageNum = response.data.pages
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
        }
      })
    },
    loadBlogs () {
      getBlogsInfo(this.pageSize, this.pageIndex).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.blogs = response.data.blogs
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
          setTimeout(function () {
            location.replace(process.env.VUE_APP_WEB_API)
          }, 2000)
        }
      })
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
