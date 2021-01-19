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
      style="background-color: #fff;color: #409eff;"
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
      default: 100
    },
    bottom: {
      type: Number,
      default: 60
    },
    right: {
      type: Number,
      default: 60
    },
    icon: {
      type: String,
      default: 'el-icon-top'
    },
    speed: {
      type: Number,
      default: 100
    },
    parentNum: {
      type: Number,
      default: 1
    }
  },
  data () {
    return {
      show: false,
      scrollEl: null
    }
  },
  mounted () {
    var btn = this.$refs.btn
    btn.style.right = this.right + 'px'
    btn.style.bottom = this.bottom + 'px'
    this.getScrollEl()
    this.scrollEl.onmousewheel = this.isShow
  },
  methods: {
    getScrollEl () {
      var btn = this.$refs.btn
      var currParent = btn
      for (var i = 0; i < this.parentNum; i++) {
        currParent = currParent.parentElement
      }
      this.scrollEl = currParent
    },
    backtop () {
      this.show = false
      var parent = this.scrollEl
      parent.scrollTop -= this.speed
      if (parent.scrollTop > 0) {
        window.requestAnimationFrame(this.backtop)
      }
    },
    isShow () {
      var scrollDom = this.scrollEl
      if (scrollDom.scrollTop > this.length) {
        this.show = true
      } else {
        this.show = false
      }
    }
  }
}
</script>

<style>
.backtop {
  position: fixed;
  right: 60px;
  bottom: 60px;
}
.el-button:hover{
  background-color: #f2f6fc !important;
}
</style>
