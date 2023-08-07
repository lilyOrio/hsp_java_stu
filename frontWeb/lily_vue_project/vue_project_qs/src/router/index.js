import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import hello from '@/components/hello'
import Lily from '@/components/Lily'
//@ 表示src目录

Vue.use(Router)

export default new Router({
  routes: [//路由表--可以指定多个
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/hello',
      name: 'hello',
      component: hello
    },
    {
      path: '/lily',
      name: 'Lily',
      component: Lily
    }
  ]
})
