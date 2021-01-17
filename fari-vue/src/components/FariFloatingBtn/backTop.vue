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
      @click="backtop"
    />
  </div>
</template>

<script>
export default {
  name: 'BackTop',
  props: {
    length: {
      type: Number,
      default: 200
    },
    bottom: {
      type: Number,
      default: 50
    },
    right: {
      type: Number,
      default: 50
    },
    icon: {
      type: String,
      default: 'el-icon-top'
    },
    speed: {
      type: Number,
      default: 100
    }
  },
  data () {
    return {
      show: false
    }
  },
  mounted () {
    var btn = this.$refs.btn
    btn.style.right = this.right + 'px'
    btn.style.bottom = this.bottom + 'px'
    this.$refs.btn.parentElement.onmousewheel = this.isShow
  },
  methods: {
    backtop () {
      this.show = false
      var parent = this.$refs.btn.parentElement
      parent.scrollTop -= this.speed
      if (parent.scrollTop > 0) {
        window.requestAnimationFrame(this.backtop)
      }
    },
    isShow () {
      if (this.$refs.btn.parentElement.scrollTop > this.length) { this.show = true } else this.show = false
    }
  }
}
</script>

<style>
.backtop {
  position: fixed;
  right: 50px;
  bottom: 50px;
}
</style>
