<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2023/12/11
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买商品</title>
</head>
<body>
<h1>购买商品</h1>
<form action="user/buy" method="get">
    购买人:<input type="text" name="username"><br>
    够买量:<input type="text" name="nums"><br>
    <input type="submit" value="购买">
</form>

<hr><h1>演示params 的使用</h1>
<a href="user/aa/bb/find?bookId=100">查询书籍</a>

<hr><h1>占位符的演示</h1>
<a href="user/reg/kristina/300">占位符的演示</a>

<hr><h1>作业</h1>
<form action="cur/save" method="post">
    品牌:<input type="text" name="brand"><br>
    价格:<input type="text" name="price"><br>
    数量:<input type="text" name="nums"><br>
    <input type="submit" value="保存">
</form>
</body>
</html>
