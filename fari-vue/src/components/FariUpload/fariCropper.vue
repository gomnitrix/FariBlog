<template>
  <el-dialog
    title="图片裁切"
    class="cropper-dialog"
    :close-on-click-modal="false"
    :visible="dialogVisible"
    center
    @close="close"
  >
    <div class="cropper-wrap">
      <div
        class="cropper-box"
        :style="cropperStyle"
      >
        <vue-cropper
          ref="cropper"
          :img="option.img"
          :output-size="option.size"
          :output-type="option.outputType"
          :info="option.info"
          :full="option.full"
          :can-scale="option.canScale"
          :can-move="option.canMove"
          :can-move-box="option.canMoveBox"
          :fixed="option.fixed"
          :fixed-number="option.fixedNumber"
          :fixed-box="option.fixedBox"
          :original="option.original"
          :auto-crop="option.autoCrop"
          :auto-crop-width="option.autoCropWidth"
          :auto-crop-height="option.autoCropHeight"
          :center-box="option.centerBox"
          :high="option.high"
          :info-true="option.infoTrue"
          :enlarge="option.enlarge"
          :mode="option.mode"
          :max-img-size="option.maxImgSize"
          @realTime="realTime"
        />
      </div>
      <div class="preview-box">
        <div class="preview-title">
          <span>预览</span>
        </div>
        <div
          :style="previewStyle"
          class="preview-img"
        >
          <div :style="preview.div">
            <img
              :style="preview.img"
              :src="preview.url"
            >
          </div>
        </div>
      </div>
    </div>
    <div
      slot="footer"
      class="dialog-footer"
    >
      <el-button @click="close">
        取 消
      </el-button>
      <el-button
        type="primary"
        :loading="loading"
        @click="finish"
      >
        确认
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
import { VueCropper } from 'vue-cropper'
export default {
  name: 'ImageCropper',
  components: {
    VueCropper
  },
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    image: {
      type: String,
      default: ''
    },
    cropperOption: {
      type: Object,
      default: () => {}
    },
    cropperStyle: {
      type: Object,
      default: () => {}
    },
    // 裁剪预览图片缩放比例
    zoom: {
      type: Number,
      default: 1
    },
    ratio: {
      type: Array,
      default: () => [1, 0.314]
    }
  },
  data () {
    return {
      // 裁剪组件的基础配置option
      option: {
        img: this.image, // 裁剪图片的地址
        outputSize: 1, // 裁剪生成图片的质量
        outputType: 'png', // 裁剪生成图片的格式
        full: false, // 是否输出原图比例的截图
        info: true, // 图片大小信息
        canScale: true, // 图片是否允许滚轮缩放
        autoCrop: true, // 是否默认生成截图框
        autoCropWidth: 375, // 默认生成截图框宽度
        autoCropHeight: 118,
        canMove: true, // 上传图片是否可以移动
        fixedBox: false, // 固定截图框大小 不允许改变
        fixed: true, // 是否开启截图框宽高固定比例
        fixedNumber: this.ratio,
        canMoveBox: true, // 截图框能否拖动
        original: false, // 上传图片按照原始比例渲染
        centerBox: true, // 截图框是否被限制在图片里面
        height: true,
        infoTrue: false, // true 为展示真实输出图片宽高 false 展示看到的截图框宽高
        enlarge: 1, // 图片根据截图框输出比例倍数
        mode: 'container', // 图片默认渲染方式
        maxImgSize: 2000
      },
      // 防止重复提交
      loading: false,
      preview: {},
      previewStyle: {}
    }
  },
  watch: {
    cropperOption: {
      handler (value) {
        this.option = Object.assign(this.option, value)
      },
      immediate: true,
      deep: true
    },
    image (newValue, oldValue) {
      this.option.img = newValue
    }
  },
  methods: {
    realTime (preview) { // 实时预览
      this.preview = preview
      this.previewStyle = {
        width: preview.w + 'px',
        height: preview.h + 'px',
        overflow: 'hidden',
        margin: '0',
        zoom: this.zoom
      }
    },
    // 将base64转换为文件
    dataURLtoFile (dataurl, filename) {
      const arr = dataurl.split(',')
      const mime = arr[0].match(/:(.*?);/)[1]
      const bstr = atob(arr[1])
      let len = bstr.length
      const u8arr = new Uint8Array(len)
      while (len--) {
        u8arr[len] = bstr.charCodeAt(len)
      }
      return new File([u8arr], filename, { type: mime })
    },
    // 将base64转换为png文件图片
    finish () {
      this.$refs.cropper.getCropData(data => {
        const file = this.dataURLtoFile(data, 'images.png')
        this.$emit('finish', file)
      })
    },
    close () {
      this.$emit('close')
    }
  }
}
</script>
<style lang="less" scoped>
.cropper-dialog  {
  & /deep/ .el-dialog{
    width: max-content;
  }
  & /deep/ .el-dialog__body {
    padding: 20px;
  }
  & /deep/ .el-button {
    width: 145px;
  }
}
.cropper-wrap{
  display: flex;
  .cropper-box{
    margin-right: 20px;
    width: 375px;
    height: 176px;
  }
  .preview-box{
    .preview-title {
      display: flex;
      min-width: 100px;
      justify-content: space-between;
      align-items: center;
      height: 32px;
      color: rgba(30,35,48,1);
      font-family: PingFangSC-Medium;
      .preveiw-upload {
        color: #0067ED;
        cursor: pointer;
      }
    }
    .preview-img{
      border-radius: 2px;
    }
  }
}
.fun-btn{
  margin-top: 16px;
  i{
    margin-right: 16px;
    font-size: 18px;
    color: #8c8c8c;
    cursor: pointer;
    &:hover{
      color: #0067ED;
    }
  }
  .reUpload{
    margin-right: 16px;
  }
}
</style>
