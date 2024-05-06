<%--
  Created by IntelliJ IDEA.
  User: uidq5850
  Date: 2024/1/16
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json提交</title>
    <!-- 引入jquery -->
    <script type="text/javascript" src="script/jquery-3.7.0.min.js"></script>
    <!-- 编写jquery 代码和请求-->
    <script type="text/javascript">
        $(function () {
                $("#getJson").click(function () {
                    //herf 是一个完整的请求地址
                    var href = this.href;
                    var args = {"time": new Date()};//防止缓存
                    //发出一个jquery的post请求，请求返回json
                    $.post(
                        href,
                        args,
                        function (data) {
                            alert(" name= " + data.name + " address= " + data.address);
                            //1. data 是json 对象
                            //2. JSON.stringify(data) 是将json 对象转成字符串
                            alert("返回数据json=" + data)
                            alert("返回数据json=" + JSON.stringify(data))
                        },
                        "json"
                    );
                    //防止重复提交
                    return false;
                });
                $("button[name='butt1']").click(function () {
                    var userName = $("#userName").val()
                    var age = $("#age").val()
                    $.ajax({
                        url: "/springmvc/save2",
                        data: JSON.stringify({"userName": userName, "age": age}),
                        type: "POST",
                        success: function (data) {
                            alert("返回的信息=" + JSON.stringify(data));
                        },
                        contentType: "application/json;charset=utf-8"
                    })
                })

            }
        )
    </script>
</head>
<body>
<h1>请求一个json数据</h1>
<a href="getJson" id="getJson">点击获取json数据</a>

<h1>发出一个json 数据</h1>
u:<input id="userName" type="text"><br/>
a:<input id="age" type="text"><br/>
<button name="butt1">添加用户</button>

<h1>下载文件的测试</h1>
<a href="downFile">点击下载文件</a>

<h1>文件上传的演示</h1>
<form action="fileUpload" method="post"
      enctype="multipart/form-data">
    文件介绍:<input type="text" name="introduce"><br>
    选择文件:<input type="file" name="file"><br>
    <input type="submit" value="上传文件">
</form>
</body>
</html>
