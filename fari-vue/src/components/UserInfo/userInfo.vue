<template>
  <div style="text-align:center; margin-top: 40px; margin-bottom:50px;">
    <el-avatar
      :src="avatarSrc"
      :size="size"
      @error="errorHandler"
    />
    <div><h1> {{ userName }}'s Blog </h1></div>
    <div> {{ summary }} </div>
  </div>
</template>

<script>
export default {
  name: 'UserInfo',
  props: {
    userName: {
      type: String,
      default: 'FariUser'
    },
    summary: {
      type: String,
      default: 'Hi! There is empty...'
    },
    size: {
      type: Number,
      default: 90
    }
  },
  data () {
    return {
      avatarSrc: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
    }
  },
  methods: {
    errorHandler () {
      this.avatarSrc = 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'
    }
  }

}
</script>
