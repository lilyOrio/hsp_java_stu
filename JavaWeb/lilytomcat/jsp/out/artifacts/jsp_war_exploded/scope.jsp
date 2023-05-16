<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
  小结：
  1域对象可以像map一样存取数据。四个域对象功能一样。不同的是它们对数据的存储范围
  2从存储范围(作用域范围看) pageContext < request < session < application
  另：
  请求转发标签：

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp 四大域对象测试-存储数据</title>
</head>
<body>
<h1>jsp 四大域对象测试-存储数据</h1>
<%
// 1.往四个域中都分别保存了数据
// 2.因为各个域空间不同，所以 key 相同也不冲突
    pageContext.setAttribute("key", "pageContext 数据");
    request.setAttribute("key", "request 数据");
    session.setAttribute("key", "session 数据");
    application.setAttribute("key", "application 数据");
%>
<h1>在本页面读取域数据的情况 </h1>
pageContext key= <%=pageContext.getAttribute("key")%> <br>
request key= <%=request.getAttribute("key")%> <br>
session key= <%=session.getAttribute("key")%> <br>
application key= <%=application.getAttribute("key")%> <br>
<jsp:forward page="/scope2.jsp"></jsp:forward>
<%
//请求转发到 scope2.jsp ， 还是一次 request
//    request.getRequestDispatcher("/scope2.jsp").forward(request,response)
//再测试一把重定向到 scope2.jsp ， 不是一次 request
//    response.sendRedirect("/jsp/scope2.jsp");
%>
</body>
</html>
