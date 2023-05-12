package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实际开发中一般不会直接访问静态资源，而是通过服务器内部转发或者重定向来访问资源
 * 演示转发定位b.html
 */
@WebServlet(urlPatterns = "/servlet03")
public class Servlet03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet03...");
//第一个"/"也可以不写，服务端会默认加上“/”；这个“/”会被解析成http://ip:port/工程名/
        request.getRequestDispatcher("/d1/d2/b.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
