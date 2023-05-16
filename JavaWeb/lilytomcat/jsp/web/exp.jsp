<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>表达式脚本的使用</title>
</head>
<body>
<h1>个人信息</h1>
<%
    String name = "lily";
    String age = "23";
    String email = request.getParameter("email");
%>
用户名：<%=name%><br/>
工作：<%="Android"%><br/>
年龄：<%=age%><br/>
电子邮件：<%=email%><br/>
</body>
</html>
