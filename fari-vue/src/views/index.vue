<template>
  <div id="idx">
    <div id="cover">
      <img
        :src="imgSrc"
        width="100%"
        height="100%"
        alt=""
      >
    </div>
    <div id="btns">
      <el-row>
        <el-button
          type="success"
          plain
          style="width:100px"
          @click="showLoignBox = true"
        >
          Login
        </el-button>
        <el-button
          type="primary"
          plain
          style="width:100px"
        >
          Register
        </el-button>
      </el-row>
      <LoginBox
        :show-loign-box="showLoignBox"
        @login-cancel="closeLogin"
      />
    </div>
  </div>
</template>

<script>
import LoginBox from '../components/LoginBox/index.vue'
export default {
  name: 'Index',
  components: {
    LoginBox
  },
  data () {
    return {
      showLoignBox: false,
      imgSrc: require('@/assets/FariBlog.jpg')
    }
  },
  methods: {
    closeLogin () {
      this.showLoignBox = false
    }
  }
}
</script>

<style scoped>
  #idx{
    width:100%;
    height:100%;
    display:flex;/*Flex布局*/
    display: -webkit-flex; /* Safari */
    align-items:center;/*指定垂直居中*/
    justify-content: center;
  }
  #cover{
    width:100%;
    height:100%;  /**宽高100%是为了图片铺满屏幕 **/
    z-index:-1;
    position: absolute;
  }
  #btns{
    z-index:1;
    position: relative;
  }
</style>
