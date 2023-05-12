package com.lilystu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 服务器创建Cookie对象，并将其返回浏览器保存
 */
@WebServlet(urlPatterns = "/create")
public class CreateCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CreateCookie...");
//        创建Cookie对象
        /*
        1."username"该Cookie的名字
        2."lily"该Cookie的值
        3.可以创建多了Cookie对象
         */
        Cookie cookie = new Cookie("username", "lily");
//        将该Cookie对象返回给浏览器
        response.setContentType("text/html;charset=utf-8");
        response.addCookie(cookie);//产生响应头：set-cookie
        PrintWriter writer = response.getWriter();
        writer.println("创建cookie成功！");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
