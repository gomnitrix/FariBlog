<template>
  <div style="height: 100%;">
    <el-container>
      <el-header>
        <FariHeader
          :user-name="userName"
          :user-id="userId"
        />
      </el-header>
      <el-main>
        <router-view />
        <el-footer id="footer">
          <div style="font-size:14px;">
            <footer>
              Copyright &copy;2020 - 2021.
            </footer>
            <footer>
              Open source on <el-link
                href="https://github.com/gomnitrix/fariblog"
                icon="fari-icon-github"
              >
                Github
              </el-link>.
            </footer>
          </div>
        </el-footer>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import FariHeader from '@c/FariHeader/fariHeader.vue'
export default {
  name: 'UserMain',
  components: {
    FariHeader
  },
  data () {
    return {
      userName: '',
      userId: ''
    }
  },
  mounted: function () {
    this.userName = this.$route.params.user
    this.userId = this.$route.params.userId
  }
}
</script>

<style scoped>
  #footer{
    /*flex 布局*/
    display: flex;
    /*实现垂直居中*/
    align-items: center;
    /*实现水平居中*/
    justify-content: center;
  }
</style>
