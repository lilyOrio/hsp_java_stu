<!--模板：表示页面视图-->
<template>
  <div>
    <h1>{{ msg }}</h1>
    <!-- element-ui 组件 -->
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
      </el-row>

      <table>
      <tr>
        <td colspan="3">1:1</td>
      </tr>
      <tr>
        <td rowspan="2">2:1</td>
        <td>2:3</td>
        <td>2:3</td>
      </tr>
      <tr>
        <td>3:2</td>
        <td>3:3</td>
      </tr>
      <tr>
        <td rowspan="2">4:1</td>
        <td>4:2</td>
        <td>4:3</td>
      </tr>
      <tr>
        <td>5:2 红烧肉<img src="@/assets/logo.png"/></td>
        <td>5:3</td>
      </tr>
    </table>
  </div>
</template>
<!--定义数据和操作方法，默认导出-->
<script>
export default {
  name: "Lily",
  data() {
    return {
      msg: "Lily饭堂",

    }
  }
}
</script>
<!--样式，css,可以去修饰页面视图-->
<style scoped>
div{
  width: 900px;
  background-color: aliceblue;
  margin: 0 auto;
}
h1{
  color: red;
}
table,tr,td{
  border: 1px solid red;
  width: 800px;
  height: 50px;
  border-collapse:collapse;
  margin: 0 auto;
}

</style>
