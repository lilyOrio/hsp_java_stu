<%--
  Created by IntelliJ IDEA.
  User: lily
  Version:1.0
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp声明脚本</title>
</head>
<body>
<h1>jsp声明脚本</h1>
<%!
    //这里我们可以声明该jsp需要使用的属性、方法、静态代码块、内部类
    //属性
    private String name = "jack";
    private int age;
    private static String company;

    //方法
    public String getName() {
        return name;
    }

    //    静态代码块
    static {
        company = "字节跳动";
    }
%>
</body>
</html>
