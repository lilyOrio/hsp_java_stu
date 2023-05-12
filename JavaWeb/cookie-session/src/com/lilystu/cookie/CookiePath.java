package com.lilystu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/cookiePath","/aaa/cookiePath"})
public class CookiePath extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CookiePath...");
        Cookie cookie1 = new Cookie("key1", "val1");
        Cookie cookie2 = new Cookie("key2", "val2");
        cookie1.setPath(request.getContextPath());//==>/cs==>默认
        cookie2.setPath(request.getContextPath()+"/aaa");//==>/cs/aaa
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("设置cookie路径成功！");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
