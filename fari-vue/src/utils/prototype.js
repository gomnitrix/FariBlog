import commonUtil from './commonUtil'

export default {
  install (Vue, options) {
    Vue.prototype.$message = commonUtil.FUNCTIONS
    Vue.prototype.$ECode = commonUtil.ECode
  }
}
