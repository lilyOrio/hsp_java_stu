package com.lilystu.cookie;

import com.lilystu.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(urlPatterns = "/readEncoderCookie")
public class ReadEncoderCookie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadEncoderCookie...");
        Cookie nameCookie = CookieUtils.getCookieByName("name", request.getCookies());
        if (nameCookie != null){
            String name = URLDecoder.decode(nameCookie.getValue(), "utf-8");
            System.out.println("name = " + name);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
