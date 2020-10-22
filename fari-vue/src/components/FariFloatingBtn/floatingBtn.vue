<template>
  <div
    ref="btn"
    class="backtop"
  >
    <el-button
      v-show="show"
      id="floatBtn"
      :icon="icon"
      :loading="loading"
      circle
      :style="btnSize"
      @click="handle"
    />
  </div>
</template>

<script>
export default {
  name: 'BackTop',
  props: {
    bottom: {
      type: Number,
      default: 200
    },
    right: {
      type: String,
      default: '50px'
    },
    icon: {
      type: String,
      default: 'el-icon-top'
    },
    loading: {
      type: Boolean,
      default: false
    },
    size: {
      type: Number,
      default: 50
    }
  },
  data () {
    return {
      show: true,
      btnSize: `font-size: ${this.size}px;`
    }
  },
  mounted () {
    var btn = this.$refs.btn
    btn.style.right = this.right
    btn.style.bottom = this.bottom + 'px'
    this.$refs.btn.parentElement.onmousewheel = this.isShow
  },
  methods: {
    handle () {
      this.$emit('clicked')
    }
  }
}
</script>

<style>
.backtop {
  position: fixed;
}
#floatBtn{
  background-color: #409eff !important;
  color: #ffffff !important;
  transition: background-color 1s;
}
#floatBtn:hover{
  background-color: #f2f6fc !important;
  color: #409eff !important;
  transition: color, background-color 0.6s;
}
</style>
