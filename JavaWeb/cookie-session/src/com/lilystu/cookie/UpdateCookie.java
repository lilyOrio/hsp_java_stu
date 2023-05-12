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

@WebServlet(urlPatterns = "/update")
public class UpdateCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UpdateCookie...");
//        方式一
//        Cookie username = new Cookie("username", "lily-hi");
//        response.addCookie(username);
//        方式二
        Cookie cookie = CookieUtils.updateCookie("username", request.getCookies(), "lily-hi");
        if (cookie != null){
            response.addCookie(cookie);
            System.out.println("修改成功！");
        }else {
            System.out.println("未找到该cookie。。。");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("UpdateCookie完成任务！");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
