package com.lilystu.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/check")
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckServlet");
        //请求转发到的Servlet用的是同一个HttpServletRequest对象
        /*
        请求转发不能访问当前WEB 工程外的资源
        重定向可以访问当前WEB 工程外的资源
         */
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        if ("tom".equals(username)){//权限分配
            request.setAttribute("role","管理员");
        }else {
            request.setAttribute("role","普通用户");
        }

//        获取分发器,参数表示需要转发到哪个Servlet，"/"会被解析成==》http://localhost:9999/servlet/
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manager");
//        将当前的request对象和response对象传递给目标Servlet；以保证两个Servlet用的是同一个request对象和response对象
//        这样才能获取到前面setAttribute的值
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
