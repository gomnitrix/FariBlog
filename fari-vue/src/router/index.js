import Router from 'vue-router'
import Vue from 'vue'
import Index from '../views/index.vue'
Vue.use(Router)

const routes = [
  {
    path: '/index',
    component: Index
  }
]

const router = new Router({
  mode: 'history',
  routes
})

export default router
