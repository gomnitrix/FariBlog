<template>
  <el-image
    v-loading="loading"
    style="width:100%;height:269px;"
    :src="cover"
    :fit="fit"
    @load="handle"
  >
    <div
      slot="error"
      class="image-slot"
    >
      <i class="el-icon-picture-outline" />
    </div>
  </el-image>
</template>
<script>
export default {
  name: 'Cover',
  props: {
    cover: {
      type: String,
      default: ''
    },
    fit: {
      type: String,
      default: 'fill'
    }
  },
  data () {
    return {
      loading: true
    }
  },
  methods: {
    handle () {
      this.loading = false
    }
  }
}
</script>
