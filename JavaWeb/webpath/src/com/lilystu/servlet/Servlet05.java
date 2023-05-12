package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过重定向方式定位user.html文件
 * 重定向是服务器发送地址给浏览器解析，所以重定向的地址解析是浏览器负责的。
 * 而请求转发的地址是服务端解析的
 */
@WebServlet(urlPatterns = "/servlet05")
public class Servlet05 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String realPath = getServletContext().getRealPath("/");
        String contextPath = getServletContext().getContextPath();
        System.out.println("realPath =" + realPath);
        System.out.println("contextPath =" + contextPath);
//        response.sendRedirect("http://localhost:9999/webpath/views/user/user.html");
//        response.sendRedirect("/webpath/views/user/user.html");
//        response.sendRedirect(contextPath + "/views/user/user.html");//推荐
        response.sendRedirect("views/user/user.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
