<template>
  <div
    id="mdEditor"
    class="vditor vditor--fullscreen"
  />
</template>

<script>
import Vditor from 'vditor'
export default {
  name: 'FariEditor',
  data () {
    return {
      editor: '',
      welcome: '# 🎉 Welcome to use FariBlog Markdown Editor (suported by Vditor)!'
    }
  },
  mounted () {
    this.editor = new Vditor('mdEditor', {
      // mode: 'sv', 默认为即时渲染模式
      // cdn: '..',
      cache: {
        enable: false
      },
      placeholder: this.welcome,
      minHeight: 600,
      width: 'auto',
      outline: true, // 大纲模式
      counter: {
        enable: 102400 // 计数，提示角标问题
      },
      toolbarConfig: {
        pin: true
      },
      // 工具栏配置具体看 vditor 使用指南
      toolbar: [
        {
          name: 'open_save',
          tip: '打开/保存',
          icon: '',
          toolbar: [
            {
              name: 'open_md',
              icon: '打开MD',
              click () {
                // this.open_md()
              }
            },
            {
              name: 'export_md',
              icon: '导出MD',
              click () {
                // return this.save_md()
              }
            }
          ]
        },
        '|',
        'emoji',
        'headings',
        'bold',
        'italic',
        'strike',
        'link',
        '|',
        'edit-mode',
        {
          name: 'more',
          toolbar: [
            {
              name: 'about',
              icon: '关于',
              click () {
                this.showAbout()
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
