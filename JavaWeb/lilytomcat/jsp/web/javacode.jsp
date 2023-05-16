<%@ page import="com.lilystu.entity.Monster" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示代码脚本</title>
</head>
<body>
<h1>演示代码脚本</h1>
<%
    //创建ArrayList
    ArrayList<Monster> monsters = new ArrayList<Monster>();
    monsters.add(new Monster(1, "牛魔王", "芭蕉扇"));
    monsters.add(new Monster(2, "孙悟空", "七十二变"));
%>
<table bgcolor="#f0f8ff" border="1px" width="300px">
    <tr>
        <th>id</th>
        <th>名字</th>
        <th>技能</th>
    </tr>
    <%
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
    %>
    <tr>
        <td><%=monster.getId()%></td>
        <td><%=monster.getName()%></td>
        <td><%=monster.getSkill()%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
