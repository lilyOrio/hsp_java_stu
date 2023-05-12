package com.lilystu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet...");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println(username + ":" + pwd);
        if ("lily".equals(username) && "123456".equals(pwd)) {
            System.out.println("登录成功！");
            Cookie user_name = new Cookie("username", "lily");
            user_name.setMaxAge(3600 * 24 * 3);
            Cookie user_pwd = new Cookie("pwd", "123456");
            user_pwd.setMaxAge(3600 * 24 * 3);
            response.addCookie(user_name);
            response.addCookie(user_pwd);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("<h1>登录成功！</h1>");
            writer.flush();
            writer.close();
        } else {
            System.out.println("登录失败！");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println("<h1>登录失败！</h1>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
