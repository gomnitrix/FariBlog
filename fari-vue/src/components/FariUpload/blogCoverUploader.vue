<template>
  <div>
    <el-upload
      ref="uploader"
      class="coverUploader"
      drag
      :auto-upload="false"
      :file-list="fileList"
      :action="uploadUrl"
      :accept="acceptType"
      :on-success="handleSuccess"
      :on-error="handleError"
      :before-upload="onBeforeUpload"
      :on-change="onFileChange"
      :data="postData"
    >
      <i class="el-icon-upload" />
      <div class="el-upload__text">
        将封面图片拖到此处，或<em>点击上传</em>
      </div>
      <div
        slot="tip"
        class="el-upload__tip"
      >
        只能上传jpg/png文件，且不超过512kb
      </div>
    </el-upload>
    <FariCropper
      ref="cropper"
      :dialog-visible="cropperVisible"
      :image="image"
      :ratio="ratio"
      @finish="onCropFinished"
      @close="closeCropper"
    />
  </div>
</template>
<script>
import * as qiniu from 'qiniu-js'
import FariCropper from '@c/FariUpload/fariCropper.vue'
import { getQiniuToken } from '@/api/blogs'
export default {
  name: 'CoverUploader',
  components: {
    FariCropper
  },
  props: {
    blogId: {
      type: String,
      default: ''
    },
    userId: {
      type: String,
      default: ''
    },
    imgType: {
      type: String,
      default: 'Cover'
    },
    ratio: {
      type: Array,
      default: () => [1, 0.314]
    }
  },
  data () {
    return {
      uploadUrl: 'https://upload-z2.qiniup.com',
      acceptType: '.jpg,.jpeg,.png,.gif',
      sizeLimit: 0.5 * 1024 * 1024, // 500Kb
      postData: {
        key: null,
        token: null,
        'x:ftype': this.imgType,
        'x:userid': this.userId,
        'x:blogid': this.blogId
      },
      copyFiles: null,
      cropperVisible: false,
      fileList: [],
      copyList: null,
      image: ''
    }
  },
  watch: {
    userId (newValue, oldValue) {
      this.postData['x:userid'] = newValue
    },
    blogId (newValue, oldValue) {
      this.postData['x:blogid'] = newValue
    },
    imgType (newValue, oldValue) {
      this.postData['x:ftype'] = newValue
    }
  },
  mounted () {
    this.init()
  },
  methods: {
    init () {
      const config = {
        useCdnDomain: true,
        region: qiniu.region.z2
      }
      const getUrl = qiniu.getUploadUrl(config)
      getUrl.then(res => {
        this.uploadUrl = res.replace(/(^undefined)/mg, 'https:')
        getQiniuToken().then((response) => {
          if (response.success === true) {
            this.postData.token = response.data.token
            var imgKey = Math.random().toString(36).slice(-8)
            this.postData.key = `/fariblog/cover/${imgKey}`
          } else {
            this.$message({
              type: 'error',
              message: response.message
            })
          }
        })
      })
    },
    handleError (res, file) {
      console.log(res)
      this.$message({
        type: 'error',
        message: res
      })
      this.isFileType(res.key)
    },
    handleSuccess (res, file) {
      console.log(res)
      if (res.success === true) {
        this.$emit('coveruid', res.data.image.uid)
      } else {
        this.handleError(res)
      }
    },
    onCropFinished (file) {
      this.cropperVisible = false
      this.copyList = Array.from(this.$refs.uploader.fileList)
      var uploadFiles = this.$refs.uploader.uploadFiles
      this.copyFiles = Object.assign({}, uploadFiles)
      var rawFile = this.copyFiles[0]
      file.uid = rawFile.uid
      this.copyFiles[0].size = file.size
      this.copyFiles[0].raw = file
      this.$refs.uploader.submit()
    },
    onFileChange (file, fileList) {
      if (this.copyFiles !== null) {
        this.$refs.uploader.uploadFiles = this.copyFiles
        fileList = this.copyFiles
        file = this.copyFiles[0]
        this.fileList = this.copyList
      }
      if (file.status !== 'ready') {
        return
      }
      var self = this
      var reader = new FileReader()
      reader.readAsDataURL(file.raw)
      reader.onload = function () {
        self.cropperVisible = true
        self.image = reader.result
      }
    },
    onBeforeUpload (file) {
      const isIMAGE = file.type === 'image/jpeg' || 'image/gif' || 'image/png'
      const isLt1M = file.size < this.sizeLimit

      if (!isIMAGE) {
        this.$message({ type: 'error', message: '上传文件只能是图片格式!' })
      }
      if (!isLt1M) {
        this.$message({ type: 'error', message: '上传文件大小不能超过500KB!' })
      }
      return isIMAGE && isLt1M
    },
    closeCropper () {
      this.cropperVisible = false
    }
  }
}
</script>
