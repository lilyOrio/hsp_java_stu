<%@ page import="org.apache.jasper.runtime.HttpJspBase" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2023/5/14
  Time: 13:17
  To change this template use File | Settings | File Templates.
  jsp文件不能直接使用浏览器解析（访问），只能通过服务端（tomcat）访问
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp的简单求和计数器</title>
</head>
<body>
<h1>jsp的简单求和计数器</h1>
<%
    //在该标签中可以写java代码
    int i = 10;
    int j = 20;
    int res = i + j;
    //jsp 中内置对象，可以直接使用
    out.println(i + " + " + j + " = " + res);
%>
</body>
</html>
