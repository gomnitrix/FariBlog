<template>
  <el-card
    style="padding: '30px 0px 0px 0px'; border-radius: 10px; margin-bottom:50px;pointer-events: none; width:860px;"
    shadow="always"
  >
    <Cover :cover="cover" />
    <div
      style="padding: 10px 60px 60px 60px;"
      v-html="blogContent"
    />
  </el-card>
</template>

<script>
import Cover from '@c/BlogCard/blogCover.vue'
export default {
  name: 'BlogCard',
  components: {
    Cover
  },
  props: {
    blogContent: {
      type: String,
      default: ''
    },
    cover: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      // fariImgSrc: require('@/assets/FariBlog.png'),
      blog: null
    }
  }

}
</script>

<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    height: 268px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }

  .clearfix:after {
      clear: both
  }
</style>
