import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/fariicon.css'
import prototype from './utils/prototype'
import xss from 'xss'

Vue.config.productionTip = false
Vue.use(prototype)
Vue.use(ElementUI)
Vue.prototype.xss = xss

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  components: { App },
  render: h => h(App),
  router
})
