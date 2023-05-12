package com.lilystu.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * cookie中文乱码问题
 * 编码
 */
@WebServlet(urlPatterns = "/encoderCookie")
public class EncoderCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EncoderCookie...");
//        Cookie cookie = new Cookie("name", "莉丽");
//        response.addCookie(cookie);//直接add会报错
//        先将改中文编码
        String lily = URLEncoder.encode("莉丽", "utf-8");
        Cookie cookie = new Cookie("name", lily);
        response.addCookie(cookie);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
