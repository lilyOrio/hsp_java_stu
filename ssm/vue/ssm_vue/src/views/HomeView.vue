<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
<!--
    在HomeView.vue引入HelloWorld组件
    这里会显示HelloWorld组件的内容
    msg="Welcome to Your Vue.js App"表示给组件HelloWorld设置了一个组件之
-->
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>
