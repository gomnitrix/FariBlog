<template>
  <div>
    <el-row style="margin-bottom: 50px">
      <el-col
        :span="width"
        :offset="offset"
      >
        <h1
          v-if="flag"
          style="text-align: center;"
        >
          {{ blog.title }}
        </h1>
        <div style="font-size: .8em;opacity: .8;text-align: center;">
          {{ meta }}
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col
        v-if="flag"
        :span="width"
        :offset="offset"
      >
        <Blog
          :blog-content="blog.content"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getBlog } from '@/api/blogs'
import Blog from '@c/BlogCard/blog.vue'
export default {
  name: 'BlogPage',
  components: {
    Blog
  },
  data () {
    return {
      width: 8,
      offset: 8,
      flag: false,
      blogId: 0,
      blog: null,
      meta: ''
    }
  },
  mounted () {
    this.blogId = this.$route.params.blogId
    this.getBlogContent(this.blogId)
  },
  methods: {
    getDate () {
      var postDate = this.blog.createTime
      var year = postDate.date.year
      var month = postDate.date.month
      var day = postDate.date.day
      return `${year}.${month}.${day}`
    },
    getBlogContent (blogId) {
      getBlog(blogId).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          this.blog = response.data.blog
          this.meta = `Posted ${this.getDate()}`
          this.flag = true
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
