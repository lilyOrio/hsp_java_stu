<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2023/12/15
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vote_ok</title>
</head>
<body>
<h1>获取的的数据显示页面</h1>
<hr>
取出request 域的数据
<br>
address: ${address }<br>
主人名字= ${requestScope.master.name }
主人信息= ${requestScope.master }
宠物名字= ${requestScope.master.pet.name }

<hr>
取出session 域的数据<br>
主人名字= ${sessionScope.master2.name }
主人信息= ${sessionScope.master2 }
</body>
</html>
