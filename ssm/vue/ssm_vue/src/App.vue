<template>
<!--
    1.App.vue这个文件可以用来布局页面
    2.<router-view/>就是路由指令，会把路由到的页面内容展示到<router-view/>
    3.路由规则，和vue2一致
    4.比如yrl地址是http://localhost:8080/那么路由的path就是 / 在src/router/index.js
-->
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
