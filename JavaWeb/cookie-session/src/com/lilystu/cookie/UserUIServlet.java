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

@WebServlet(urlPatterns = "/userUIServlet")
public class UserUIServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserUIServlet...");
        Cookie[] cookies = request.getCookies();
        Cookie usernameCookie = CookieUtils.getCookieByName("username", cookies);
        Cookie pwdCookie = CookieUtils.getCookieByName("pwd", cookies);
        String username = "";
        String pwd = "";
        if (usernameCookie != null && pwdCookie != null) {
            username = usernameCookie.getValue();
            pwd = pwdCookie.getValue();
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>登录界面</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>用户登录界面</h1>\n" +
                "<form action=\"http://localhost:9999/cs/loginServlet\" method=\"post\">\n" +
                "    u:<input type=\"text\" value=\"" + username + "\" name=\"username\"><br/>\n" +
                "    p:<input type=\"password\" value=\"" + pwd + "\" name=\"pwd\"><br/>\n" +
                "    <input type=\"submit\" value=\"登录\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
