<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp计算器</title>
<%--使用js+正则表达式完成数据校验--%>
    <script type="text/javascript">
        function check(){
            //得到num1和num2的值
            var num1 = document.getElementById("num1").value;
            var num2 = document.getElementById("num2").value;
        //验证正则表达式
            var reg = /^[-]?([1-9]\d*|0)$/;
            if (!reg.test(num1)){
                alert("num1不是一个整数");
                return false;
            }
            if (!reg.test(num2)){
                alert("num2不是一个整数");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h1>jsp计算器</h1>
<form action="<%=request.getContextPath()%>/calServlet" method="post" onsubmit="return check()">
    num1:<input type="text" id="num1" name="num1"><br/>
    num2:<input type="text" id="num2" name="num2"><br/>
    运算符号：
    <select name="oper">
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="x">x</option>
        <option value="÷">÷</option>
    </select><br/>
    <input type="submit" name="计算">
</form>
</body>
</html>
