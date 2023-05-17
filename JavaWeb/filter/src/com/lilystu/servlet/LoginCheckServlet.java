package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/login")
public class LoginCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginCheckServlet");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            request.getRequestDispatcher("/manager/admin.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
