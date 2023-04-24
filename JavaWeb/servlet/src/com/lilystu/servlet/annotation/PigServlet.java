package com.lilystu.servlet.annotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/pig1", "/pig2"},loadOnStartup = 1)
public class PigServlet extends HttpServlet {
    private int post_count = 0;
    private int get_count = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String remoteAddr = request.getRemoteAddr();
        System.out.println("PigServlet POST 请求~~" + ++post_count + "\n" +
                "浏览器IP：" + remoteAddr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String remoteAddr = request.getRemoteAddr();
        System.out.println("PigServlet GET 请求~~" + ++get_count + "\n" +
                "浏览器IP：" + remoteAddr);
    }
}
