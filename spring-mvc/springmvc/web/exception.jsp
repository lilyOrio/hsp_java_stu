<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2024/1/24
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异常信息</title>
</head>
<body>
<h1>测试异常</h1>
<a href="testException01?num=0">点击测试异常</a><br><br>
<a href="testGlobalException">点击测试全局异常</a><br><br>
<a href="testException02">点击测试自定义异常</a><br/><br/>
<a href="testException03">测试统一的异常[SimpleMappingExceptionResolver]</a><br/><br/>
<a href="testException04">统一未归类异常</a>
</body>
</html>
