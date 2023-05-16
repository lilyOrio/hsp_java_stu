<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>妖怪信息</title>
</head>
<body>
<h1>显示妖怪信息</h1>
<table bgcolor="#f0f8ff" border="1px" width="300px">
    <tr>
        <th>id</th>
        <th>名字</th>
        <th>技能</th>
    </tr>
    <c:forEach items="${requestScope.monsters}" var="monster">
        <c:if test="${monster.id > 100}">
            <tr>
                <td>${monster.id}</td>
                <td>${monster.name}</td>
                <td>${monster.skill}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
