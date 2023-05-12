package com.lilystu.cookie;

import com.lilystu.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/readByName")
public class ReadCookieByName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadCookieByName...");
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.getCookieByName("JSESSIONID", cookies);
        if (cookie != null){
            System.out.println("JSESSIONID = " + cookie.getValue());
        }else {
            System.out.println("未读取到cookie。。。");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("完成读取任务！");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
