package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        用户名+密码
        String username = request.getParameter("username");
        String pwd1 = request.getParameter("pwd1");
        String pwd2 = request.getParameter("pwd2");
//        喜欢的运动
        String[] sports = request.getParameterValues("sport");
        String hobby = null;
        for (String sport : sports) {
            hobby = sport + " ";
        }
//        性别
        String[] genders = request.getParameterValues("gender");
        System.out.println(genders[0]);
//        喜欢的城市
        String[] cities = request.getParameterValues("city");
        System.out.println(cities[0]);
//        自我介绍
        String introduction = request.getParameter("introduction");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("用户名：" + username + "<br/>密码：" + pwd1 + "<br/>密码确认：" + pwd2);
        writer.println("<br/>喜欢的运动：" + hobby);
        writer.println("<br/>性别：" + genders[0]);
        writer.println("<br/>喜欢的城市："+cities[0]);
        writer.println("<br/>自我介绍："+introduction);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
