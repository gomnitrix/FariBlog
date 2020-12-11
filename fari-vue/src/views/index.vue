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
          @click="showRegisterBox = true"
        >
          Register
        </el-button>
      </el-row>
      <LoginBox
        :show-loign-box="showLoignBox"
        @login-cancel="closeLogin"
      />
      <RegisterBox
        :show-register-box="showRegisterBox"
        @register-cancel="closeRegister"
      />
    </div>
  </div>
</template>

<script>
import LoginBox from '@c/LoginBox/index.vue'
import RegisterBox from '@c/LoginBox/registerBox.vue'
export default {
  name: 'Index',
  components: {
    LoginBox,
    RegisterBox
  },
  data () {
    return {
      showLoignBox: false,
      showRegisterBox: false,
      fariImgSrc: require('@/assets/FariBlog.png')
    }
  },
  methods: {
    closeLogin () {
      this.showLoignBox = false
    },
    closeRegister () {
      this.showRegisterBox = false
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
