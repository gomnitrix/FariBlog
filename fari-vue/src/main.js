import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import prototype from './utils/prototype'

Vue.config.productionTip = false
Vue.use(prototype)
Vue.use(ElementUI)

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  render: h => h(App),
  router
})
