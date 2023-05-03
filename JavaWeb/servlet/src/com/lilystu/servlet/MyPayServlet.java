package com.lilystu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/myPayServlet")
public class MyPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String money = request.getParameter("money");
        int money1 = WebUtils.parseString(money);
        ServletContext servletContext = getServletContext();
        if (money1>100){
            response.sendRedirect(servletContext.getContextPath()+"/payOk.html");
        }else {
            response.sendRedirect(servletContext.getContextPath()+"/pay.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
