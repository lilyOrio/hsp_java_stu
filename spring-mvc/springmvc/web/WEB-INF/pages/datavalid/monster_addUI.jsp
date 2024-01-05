<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2024/1/3
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>添加妖怪~~</h3>
<!-- 这里的表单，我们使用springMVC 的标签来完成
特别说明几点:
1. SpringMVC 表单标签在显示之前必须在request 中有一个bean, 该bean 的属性和表单标签的字段要对应!
    request 中的key 为: form 标签的modelAttrite 属性值， 比如这里的monsters
2. SpringMVC 的form:form 标签的action 属性值中的/ 不代表WEB 应用的根目录.
-->
<form:form action="save" method="POST" modelAttribute="monster">
    妖怪名字: <form:input path="name"/> <form:errors path="name"/><br><br>
    妖怪年龄~: <form:input path="age"/> <form:errors path="age"/><br><br>
    妖怪生日: <form:input path="birthday"/> <form:errors path="birthday"/>要求以"9999-11-11"的形式<br><br>
    妖怪工资: <form:input path="salary"/> <form:errors path="salary"/>要求以"123,890.12"的形式<br><br>
    电子邮件: <form:input path="email"/> <br><br>
    <input type="submit" value="添加妖怪"/>
</form:form>
</body>
</html>
