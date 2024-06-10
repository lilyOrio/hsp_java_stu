<template>
    <!--
        1.App.vue这个文件可以用来布局页面
        2.<router-view/>就是路由指令，会把路由到的页面内容展示到<router-view/>
        3.路由规则，和vue2一致
        4.比如yrl地址是http://localhost:8080/那么路由的path就是 / 在src/router/index.js
    -->
    <!--  <nav>-->
    <!--    <router-link to="/">Home</router-link> |-->
    <!--    <router-link to="/about">About</router-link>-->
    <!--  </nav>-->
    <!--  <router-view/>-->

    <div>
        <!--引入Header 头部-->
        <Header/>
        <!--侧边栏-->
        <div style="display: flex">
            <Aside/>
        <!--  内容区域，通过路由获取   -->
            <router-view style="flex: 1"/>
        </div>
    </div>
</template>

<style>
</style>
<script>
    import Header from "@/components/Header";
    import Aside from "@/components/Aside";

    export default {
        components: {Aside, Header}
    }
</script>