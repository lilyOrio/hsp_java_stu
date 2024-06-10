import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

//由于配置路由
const routes = [
  {
    path: '/',//路径
    name: 'home',
    component: HomeView//http://localhost:8080/就路由到这个组件，就会把HomeView组件的内容返回到 <router-view/>
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
