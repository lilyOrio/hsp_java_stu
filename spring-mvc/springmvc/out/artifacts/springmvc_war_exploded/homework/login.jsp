<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2023/12/19
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login/doLogin">
    u:<input name="username" type="text"> <br/>
    p:<input name="password" type="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
