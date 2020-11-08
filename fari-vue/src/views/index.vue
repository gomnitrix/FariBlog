<template>
  <div id="idx">
    <div id="cover">
      <img
        :src="fariImgSrc"
        width="100%"
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
      fariImgSrc: require('@/assets/FariBlog.png')
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
    z-index:0;
    position: absolute;
  }
  #btns{
    z-index:1;
    position: relative;
  }
</style>
