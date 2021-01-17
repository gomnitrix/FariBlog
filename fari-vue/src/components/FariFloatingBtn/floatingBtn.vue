<template>
  <div
    ref="btn"
    class="backtop"
  >
    <el-button
      v-show="show"
      type="primary"
      :icon="icon"
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
</style>
