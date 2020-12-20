<template>
  <div>
    Hello! User {{ $route.params.userId }}
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
  </div>
</template>

<script>
import BlogCard from '@c/BlogCard/blogCard.vue'
import { getBlogsInfo } from '@/api/blogs'
export default {
  name: 'Home',
  components: {
    BlogCard
  },
  data () {
    return {
      blogs: [],
      pageSize: 8,
      pageIndex: 1
    }
  },
  mounted: function () {
    this.loadBlogs()
  },
  methods: {
    loadBlogs () {
      getBlogsInfo(this.pageSize, this.pageIndex).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.blogs = response.data.blogs
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
