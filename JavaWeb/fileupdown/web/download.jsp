<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2023/5/31
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>文件下载</title>
    <base href="<%=request.getContextPath()+"/"%>>">
</head>
<body>
<h1>文件下载</h1>
<a href="fileDownloadServlet?name=小约翰1.jpeg">点击下载小狗图片</a><br/><br/>
<a href="fileDownloadServlet?name=韩顺平零基础Java笔记.pdf">点击下载韩顺平零基础Java笔记.pdf</a><br/><br/>
</body>
</html>
