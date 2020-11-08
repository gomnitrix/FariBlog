import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import commonUtil from './utils/commonUtil'

Vue.config.productionTip = false
Vue.prototype.$commonUtil = commonUtil.FUNCTIONS
Vue.prototype.$ECode = commonUtil.ECode
Vue.use(ElementUI)

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  render: h => h(App),
  router
})
