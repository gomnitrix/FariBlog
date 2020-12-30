import Router from 'vue-router'
import Vue from 'vue'
import Index from '@/views/index'
import Home from '@/views/home'
import Main from '@/views/main'
import FariEditor from '@/views/blogEdit'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Main,
    children: [
      { path: '', component: Index },
      { path: '/index/:userId', component: Home },
      { path: '/blog/editor', component: FariEditor }
    ]
  }
]

const router = new Router({
  mode: 'history',
  routes: routes
})

export default router
