<template>
  <el-card
    :body-style="{ padding: '0px' }"
    shadow="hover"
  >
    <img
      :src="fariImgSrc"
      class="image"
    >
    <div style="padding: 14px;">
      <h2>{{ blogInfo.title }}</h2>
      <div class="bottom clearfix">
        <p> {{ blogInfo.description }} </p>
        <time class="time">{{ blogInfo.postTime }}</time>
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
          description: 'description',
          postTime: 'postTime'
        }
      }
    }
  },
  data () {
    return {
      fariImgSrc: require('@/assets/FariBlog.png')
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
