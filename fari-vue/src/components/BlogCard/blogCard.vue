<template>
  <el-card
    :body-style="{ padding: '0px' }"
    style="padding: '30px 0px 0px 0px'; border-radius: 10px;"
    shadow="hover"
  >
    <img
      :src="fariImgSrc"
      class="image"
    >
    <div style="padding: 10px 60px 60px 60px;">
      <h2><strong>{{ blogInfo.title }}</strong></h2>
      <p> {{ blogInfo.summary }} </p>
      <time class="time"> Posted {{ getDate() }}</time>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'BlogCard',
  props: {
    blogInfo: {
      type: Object,
      default: function () {
        return {
          title: 'title',
          summary: 'description',
          createTime: 'postTime'
        }
      }
    }
  },
  data () {
    return {
      fariImgSrc: require('@/assets/FariBlog.png')
    }
  },
  methods: {
    getDate () {
      var postDate = this.blogInfo.createTime
      var year = postDate.date.year
      var month = postDate.date.month
      var day = postDate.date.day
      return `${year}.${month}.${day}`
    }
  }
}
</script>

<style>
  .el-card:hover{
    margin-top: -5px;
  }

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
