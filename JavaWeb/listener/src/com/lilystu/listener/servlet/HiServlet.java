package com.lilystu.listener.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/hi")
public class HiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//操作servletContextAttribute属性
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("name","lily");
        servletContext.setAttribute("name","lily~");
        servletContext.removeAttribute("name");
        HttpSession session = request.getSession();
        System.out.println(session);
        session.setAttribute("name","lily");
        session.setAttribute("name","lily~");
        session.removeAttribute("name");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
