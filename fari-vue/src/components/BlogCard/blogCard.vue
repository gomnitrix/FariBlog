<template>
  <el-card
    :body-style="{ padding: '0px' }"
    style="padding: '30px 0px 0px 0px'; border-radius: 10px; margin-bottom:50px;"
    shadow="hover"
  >
    <div
      style="cursor:pointer;"
      @click="getBlog"
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
          createTime: 'postTime',
          uid: 'blogId'
        }
      }
    }
  },
  data () {
    return {
      fariImgSrc: require('@/assets/FariBlog.png'),
      userName: this.$route.params.user,
      userId: this.$route.params.userId
    }
  },
  methods: {
    getDate () {
      var postDate = this.blogInfo.createTime
      var year = postDate.date.year
      var month = postDate.date.month
      var day = postDate.date.day
      return `${year}.${month}.${day}`
    },
    getBlog () {
      this.$router.push({
        name: 'Blog',
        params: {
          user: this.userName,
          userId: this.userId,
          blogId: this.blogInfo.uid
        }
      })
    }
  }
}
</script>

<style>
  .el-card:hover{
    box-shadow: 1px 10px 30px 0 rgba(0,0,0,.1);
    transition-delay: 0s !important;
    transform: translateY(-5px);
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
