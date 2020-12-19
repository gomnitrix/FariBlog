import Router from 'vue-router'
import Vue from 'vue'
import Index from '@/views/index'
import Home from '@/views/home'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Index
  },
  {
    path: '/index/:userId',
    component: Home
  }
]

const router = new Router({
  routes: routes
})

export default router
