import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home'
import FriendChat from '../views/chat/FriendChat'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },{
    path: '/home',
    name: '首页',
    component: Home,
    children: [
      {
        path: '/chat',
        name: '在线聊天',
        component: FriendChat,
        hidden: true
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
