import { Message } from 'element-ui'
const ECode = {
  SUCCESS: 0,
  ERROR: 1
}

const FUNCTIONS = {
  /**
   * 通用提示信息
   * @type {{success: message.success, warning: message.warning, error: message.error, info: message.info}}
   */
  message: {
    success: function (message) {
      Message({
        showClose: true,
        message: message || '成功',
        type: 'success'
      })
    },
    warning: function (message) {
      Message({
        showClose: true,
        message: message || '警告',
        type: 'warning'
      })
    },
    info: function (message) {
      Message({
        showClose: true,
        message: message || '提示',
        type: 'info'
      })
    },
    error: function (message) {
      Message({
        showClose: true,
        message: message || '异常',
        type: 'error'
      })
    }
  }
}

export default {
  ECode,
  FUNCTIONS
}
