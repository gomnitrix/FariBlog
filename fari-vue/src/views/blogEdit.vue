<template>
  <div>
    <el-drawer
      ref="drawer"
      :before-close="handleClose"
      direction="ltr"
      custom-class="demo-drawer"
    >
      <div class="demo-drawer__content">
        <el-form
          :rules="blogInfoRules"
          :model="blogInfoForm"
        >
          <el-form-item
            label="Blog Title"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="form.title"
              autocomplete="off"
            />
          </el-form-item>
          <el-form-item
            label="Blog Summary"
            :label-width="formLabelWidth"
          >
            <el-input
              v-model="form.summary"
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
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">
            取 消
          </el-button>
          <el-button
            type="primary"
            :loading="loading"
            @click="$refs.drawer.closeDrawer()"
          >
            {{ loading ? '提交中 ...' : '发 布' }}
          </el-button>
        </div>
      </div>
    </el-drawer>
    <div
      id="mdEditor"
      class="vditor"
    />
  </div>
</template>

<script>
import Vditor from 'vditor'
export default {
  name: 'FariEditor',
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
      loading: false
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
      width: '50%',
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
        {
          name: 'submit',
          tipPosition: 's',
          tip: '提交博客',
          className: 'right',
          icon: '<svg t="1610721877054" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="706" width="200" height="200"><path d="M636.096 1016.96c-26.624 0-51.2-14.336-65.536-36.864l-204.8-329.728-327.68-200.704C13.44 433.28-2.88 404.608 1.216 373.888c4.096-30.72 22.528-55.296 51.2-65.536L902.336 5.248c28.672-10.24 61.44-2.048 83.968 20.48 22.528 20.48 30.72 53.248 20.48 83.968l-296.96 854.016c-10.24 28.672-34.816 49.152-65.536 51.2-2.048 2.048-6.144 2.048-8.192 2.048z m-204.8-413.696l202.752 327.68L928.96 85.12l-2.048-2.048-839.68 301.056 337.92 206.848 6.144 12.288c0-2.048 0-2.048 0 0z" p-id="707"></path><path d="M928.96 25.984l58.24 57.664-564.992 570.368-58.24-57.6z" p-id="708"></path></svg>',
          click () {
            self.submit()
          }
        },
        'record',
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
        message: '这是基于Vditor实现的Web端MarkDown工具<br/>🎊欢迎使用~<br/>'
      })
    },
    submit () {
      var htmlContent = this.editor.getHTML()
      console.log('html content: ')
      console.log(htmlContent)
    },
    post () {

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
</style>
