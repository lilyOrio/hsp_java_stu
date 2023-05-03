package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/downServlet")
public class DownServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("DownServlet...");
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = response.getWriter();
//        writer.println("hi~~~");
//        writer.flush();
//        writer.close();

//        完成自己的业务
//        发出请求重定向==》DownServletNew
//        1.sendRedirect会返回一个302状态码和地址（由浏览器解析）
//        2.浏览器在收到/servlet/downServletNew后会自动解析成http://localhost:9999/servlet/downServletNew
//        response.sendRedirect("/servlet/downServletNew");
//        不能共享Request域对象，两次请求的Request对象是不一样的
//        可以重定向到web应用以外的资源
//        response.sendRedirect("http://www.baidu.com");

//        方式二：
        response.setStatus(302);
        response.setHeader("Location","/servlet/downServletNew");
//        动态获取到application context
        String contextPath = getServletContext().getContextPath();
        System.out.println(contextPath);// ==>/servlet
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
