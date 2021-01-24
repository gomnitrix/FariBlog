<template>
  <div
    class="wrapper"
    style="overflow: auto;width: 100%;height: 100%;margin-bottom:40px;"
  >
    <FariFloatingBtn
      :display="true"
      right="80px"
      :icon="btnIcon"
      :loading="loading"
      @clicked="method"
    />
    <el-col
      :span="16"
      :offset="4"
    >
      <div>
        <el-row>
          <el-form
            ref="blogInfoForm"
            :rules="blogInfoRules"
            :model="blogInfoForm"
            label-position="right"
          >
            <el-form-item
              label="Title"
              prop="title"
            >
              <el-input
                v-model="blogInfoForm.title"
                autocomplete="off"
              />
            </el-form-item>
            <el-row>
              <el-form-item
                label="Summary"
                prop="summary"
              >
                <el-input
                  v-model="blogInfoForm.summary"
                  type="textarea"
                  :rows="2"
                  autocomplete="off"
                  placeholder="请输入博客简介，字数不超过150字。"
                />
              </el-form-item>
              <CoverUploader
                :user-id="userId"
                :blog-id="blogId"
                @coveruid="setCover"
              />
            </el-row>
            <!-- <el-form-item
            label="Blog Summary"
            :label-width="formLabelWidth"
          >
            <el-select
              v-model="form.summary"
              placeholder="请输入博客简介，字数不超过150字。"
            >
              <el-option
                label="区域一"
                value="shanghai"
              />
              <el-option
                label="区域二"
                value="beijing"
              />
            </el-select>
          </el-form-item> -->
          </el-form>
        </el-row>
        <el-row>
          <div
            id="mdEditor"
            class="vditor"
          />
        </el-row>
      </div>
    </el-col>
  </div>
</template>

<script>
import Vditor from 'vditor'
import FariFloatingBtn from '@c/FariFloatingBtn/floatingBtn.vue'
import CoverUploader from '@c/FariUpload/blogCoverUploader.vue'
import { postBlog, updateBlog, getBlog, getQiniuToken } from '@/api/blogs'
import * as qiniu from 'qiniu-js'
export default {
  name: 'FariEditor',
  components: {
    FariFloatingBtn,
    CoverUploader
  },
  data () {
    return {
      editor: '',
      welcome: '# 🎉 Welcome to use FariBlog Markdown Editor (suported by Vditor)!',
      blogInfoForm: {
        title: '',
        summary: '',
        coverUid: ''
      },
      blogInfoRules: {
        title: [
          { required: true, message: '请输入博客标题', trigger: 'blur' },
          { min: 1, message: '用户名长度大于等于 1 个字符', trigger: 'blur' },
          { max: 50, message: '用户名长度不能大于 20 个字符', trigger: 'blur' }
        ],
        summary: [
          { required: true, message: '请输入博客摘要', trigger: 'blur' },
          { min: 1, message: '密码长度需要大于等于 1 个字符', trigger: 'blur' },
          { max: 150, message: '密码长度不能大于 150 个字符', trigger: 'blur' }
        ]
      },
      loading: false,
      btnIcon: 'el-icon-s-promotion',
      dialog: true,
      timer: null,
      userName: null,
      userId: '',
      blogId: this.$route.params.blogId,
      method: this.post,
      ossDomain: 'http://qnahkr248.hn-bkt.clouddn.com/'
    }
  },
  mounted () {
    const self = this
    this.userName = this.$route.params.user
    this.userId = this.$route.params.userId
    this.editor = new Vditor('mdEditor', {
      cache: {
        enable: false
      },
      upload: {
        accept: 'image/*',
        max: 2 * 1024 * 1024,
        token: 'test',
        filename (name) {
          // eslint-disable-next-line no-useless-escape
          return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, '')
            // eslint-disable-next-line no-useless-escape
            .replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, '')
            .replace('/\\s/g', '')
        },
        handler (files) {
          var token = null
          getQiniuToken().then((response) => {
            if (response.success === true) {
              token = response.data.token
              if (typeof token === 'undefined' || token === null) return
              for (var i = 0; i < files.length; i++) {
                console.log(files[i])
                var file = files[i]
                var key = null
                var putExtra = {
                  fname: file.name,
                  mimeType: file.type
                }
                var config = {
                  useCdnDomain: true,
                  region: qiniu.region.z2,
                  checkByMD5: true
                }
                const observable = qiniu.upload(file, key, token, putExtra, config)
                const observer = {
                  next (res) {

                  },
                  error (err) {
                    self.$message({
                      type: 'error',
                      message: err.message
                    })
                  },
                  complete (res) {
                    var url = self.ossDomain + res.key
                    var ImgMd = `![](${url})`
                    self.editor.insertValue(ImgMd)
                  }
                }
                observable.subscribe(observer)
              }
            } else {
              self.$message({
                type: 'error',
                message: response.message
              })
            }
          })
        }
      },
      placeholder: self.welcome,
      minHeight: 1000,
      width: '100%',
      outline: true, // 大纲模式
      counter: {
        enable: 102400 // 计数，提示角标问题
      },
      // toolbarConfig: {
      //   pin: true
      // },
      toolbar: [
        'emoji',
        'headings',
        'bold',
        'italic',
        'strike',
        'link',
        '|',
        'list',
        'ordered-list',
        'check',
        'outdent',
        'indent',
        '|',
        'quote',
        'line',
        'code',
        'inline-code',
        'insert-before',
        'insert-after',
        '|',
        'upload',
        'table',
        '|',
        'undo',
        'redo',
        '|',
        'fullscreen',
        'edit-mode',
        {
          name: 'more',
          toolbar: [
            'both',
            'code-theme',
            'content-theme',
            'export',
            'outline',
            'preview',
            'devtools',
            'info',
            'help',
            {
              name: 'about',
              icon: '关于',
              click () {
                self.showAbout()
              }
            }
          ]
        }],
      preview: {
        delay: 200
      }
    })
    // 对于博客修改的情况进行相应初始化
    self.checkIfUpdate()
  },
  methods: {
    showAbout () {
      this.$message({
        type: 'info',
        message: '这是基于Vditor实现的Web端MarkDown工具，🎊欢迎使用~'
      })
    },
    submit () {
      var htmlContent = this.editor.getHTML()
      console.log('html content: ')
      console.log(htmlContent)
    },
    checkIfUpdate () {
      if (typeof this.$route.params.blogId === 'undefined') return
      var blogId = this.$route.params.blogId
      this.method = this.update
      getBlog(blogId).then(response => {
        if (response.code === this.$ECode.SUCCESS) {
          var blog = response.data.blog
          this.blogInfoForm.title = blog.title
          this.blogInfoForm.summary = blog.summary
          this.blogInfoForm.coverUid = blog.coverUid
          var content = this.editor.html2md(blog.content)
          this.editor.setValue(content)
        } else {
          this.$message({
            type: 'error',
            message: response.message
          })
        }
      })
    },
    getParams () {
      var params = {}
      params.title = this.blogInfoForm.title
      params.summary = this.blogInfoForm.summary
      params.content = this.editor.getHTML()
      params.author = this.$route.params.user
      params.authorId = this.$route.params.userId
      params.coverUid = this.blogInfoForm.coverUid
      return params
    },
    post: function () {
      this.$refs.blogInfoForm.validate((valid) => {
        if (!valid) {
          console.log('表单校验失败。')
        } else {
          this.loading = true
          var params = this.getParams()
          console.log(params)
          postBlog(params).then(response => {
            if (response.success === true) {
              this.$message({
                type: 'success',
                message: response.message
              })
              setTimeout(() => {
                this.$router.push({
                  name: 'Home',
                  params: {
                    user: this.userName,
                    userId: this.userId
                  }
                })
              }, 2000)
            } else {
              this.$message({
                type: 'error',
                message: response.message
              })
            }
            this.loading = false
          })
        }
      }
      )
    },
    // 更新当前博客标题，正文等内容
    update: function () {
      this.$refs.blogInfoForm.validate((valid) => {
        if (!valid) {
          console.log('表单校验失败。')
        } else {
          this.loading = true
          var params = this.getParams()
          params.uid = this.$route.params.blogId
          updateBlog(params).then(response => {
            if (response.code === this.$ECode.SUCCESS) {
              this.$message({
                type: 'success',
                message: response.message
              })
              setTimeout(() => {
                this.$router.push({
                  name: 'Home',
                  params: {
                    user: this.userName,
                    userId: this.userId
                  }
                })
              }, 2000)
            } else {
              this.$message({
                type: 'error',
                message: response.message
              })
            }
            this.loading = false
          })
        }
      }
      )
    },
    setCover (data) {
      this.blogInfoForm.coverUid = data
    }
    // async open_md () {
    //   const filePath = await dialog.open({
    //     filter: 'md'
    //   })
    //   // Notification.info(JSON.stringify(file_path))
    //   const result = await readTextFile(filePath)
    //   // Notification.info(JSON.stringify(result))
    //   this.vditor.setValue(result)
    // },
    // async save_md () {
    //   const filePath = await dialog.save({
    //     filter: 'md'
    //   })
    //   await writeFile({
    //     path: filePath,
    //     contents: this.vditor.getValue()
    //   })
    // },
  }
}
</script>
<style scoped>
@import "~vditor/dist/index.css";
#postBtn {
   font-size: 50px;
}
</style>
