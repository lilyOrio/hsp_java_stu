<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>计算结果</title>
</head>
<body>
<h1>计算结果</h1>
<%=request.getAttribute("res")%><br/>
<a href="<%=request.getContextPath()%>/cal/calUI.jsp">再来一次</a>
</body>
</html>
