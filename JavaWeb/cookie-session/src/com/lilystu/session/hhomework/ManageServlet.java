package com.lilystu.session.hhomework;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/manage")
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManageServlet...");
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");

        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/userlogin.html");
            return;
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>用户管理页面</h1>");
        writer.write("欢迎你: 管理员 " + username);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
