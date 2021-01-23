<template>
  <el-upload
    class="coverUploader"
    drag
    :action="uploadUrl"
    :accept="acceptType"
    :on-success="handleSuccess"
    :on-error="handleError"
    :before-upload="onBeforeUpload"
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
      只能上传jpg/png文件，且不超过500kb
    </div>
  </el-upload>
</template>
<script>
import * as qiniu from 'qiniu-js'
import { getQiniuToken } from '@/api/blogs'
export default {
  name: 'CoverUploader',
  props: {
    blogId: {
      type: String,
      default: ''
    },
    userId: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      uploadUrl: 'https://upload-z2.qiniup.com',
      acceptType: '.jpg,.jpeg,.png,.gif',
      sizeLimit: 1 * 1024 * 1024, // 1Mb
      postData: {
        key: null,
        token: null,
        'x:ftype': 'Cover',
        'x:userid': this.userId,
        'x:blogid': this.blogId
      }
    }
  },
  watch: {
    userId (newValue, oldValue) {
      console.log('userID new value:')
      console.log(newValue)
      this.postData['x:userid'] = newValue
      console.log(this.postData)
    },
    blogId (newValue, oldValue) {
      console.log('blogId new value:')
      console.log(newValue)
      this.postData['x:blogid'] = newValue
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
    onBeforeUpload (file) {
      const isIMAGE = file.type === 'image/jpeg' || 'image/gif' || 'image/png'
      const isLt1M = file.size < this.sizeLimit

      if (!isIMAGE) {
        this.$message({ type: 'error', message: '上传文件只能是图片格式!' })
      }
      if (!isLt1M) {
        this.$message({ type: 'error', message: '上传文件大小不能超过1MB!' })
      }
      return isIMAGE && isLt1M
    }
  }
}
</script>
