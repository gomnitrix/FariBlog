<template>
  <div
    class="wrapper"
    style="overflow: auto;width: 100%;height: 100%;"
  >
    <FariFloatingBtn
      :display="true"
      right="13.333%"
      :icon="btnIcon"
      @clicked="post"
    />
    <!-- <el-backtop
      target=".wrapper"
      :visibility-height="0"
      :bottom="200"
      style="right:13.333%;"
      @click="submit()"
    >
      <el-button
        id="postBtn"
        size="medium"
        type="primary"
        :icon="btnIcon"
        circle
      />
    </el-backtop> -->
    <el-col
      :span="16"
      :offset="4"
    >
      <div style="margin-left:20%; margin-right:20%;">
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
import { postBlog } from '@/api/blogs'
export default {
  name: 'FariEditor',
  components: {
    FariFloatingBtn
  },
  data () {
    return {
      editor: '',
      welcome: '# 🎉 Welcome to use FariBlog Markdown Editor (suported by Vditor)!',
      blogInfoForm: {
        title: '',
        summary: ''
      },
      blogInfoRules: {
        title: [
          { required: true, message: '请输入博客标题', trigger: 'blur' },
          { min: 1, message: '用户名长度大于等于 1 个字符', trigger: 'blur' },
          { max: 20, message: '用户名长度不能大于 20 个字符', trigger: 'blur' }
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
      timer: null
    }
  },
  mounted () {
    const self = this
    this.editor = new Vditor('mdEditor', {
      cache: {
        enable: false
      },
      placeholder: this.welcome,
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
      },
      after: () => {
        // this.vditor.setValue('# 🎉️ Welcome to use Tauri Vditor!')
      }
    })
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
    post: function () {
      this.$refs.blogInfoForm.validate((valid) => {
        if (!valid) {
          console.log('表单校验失败。')
        } else {
          this.loading = true
          this.btnIcon = 'el-icon-loading'
          var params = {}
          params.title = this.blogInfoForm.title
          params.summary = this.blogInfoForm.summary
          params.content = this.editor.getHTML()
          params.author = this.$route.params.user
          params.authorId = this.$route.params.userId
          postBlog(params).then(response => {
            if (response.code === this.$ECode.SUCCESS) {
              this.$message({
                type: 'success',
                message: response.message
              })
              setTimeout(function () {
                console.log(process.env.VUE_APP_WEB_API + `/index/${this.$route.params.userId}`)
                location.replace(process.env.VUE_APP_WEB_API + `/index/${this.$route.params.userId}`)
              }, 2000)
            } else {
              this.$message({
                type: 'error',
                message: response.message
              })
            }
            this.loading = false
            this.btnIcon = 'el-icon-loading'
          })
        }
      }
      )
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
