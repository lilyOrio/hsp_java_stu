package com.lily_stu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class T1Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("T1Servlet!");
        //    如果有一个请求过来，就把业务转给hi.html处理(重定向)
        //1.返回302状态码
        // 2.返回响应location:/hi.html 重定向到web下的hi.html文件
//        response.sendRedirect("/http/hi.html");
        response.sendRedirect("https://www.baidu.com/");//重定向到百度

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
