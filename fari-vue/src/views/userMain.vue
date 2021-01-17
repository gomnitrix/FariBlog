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
      </el-main>
      <el-footer />
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
