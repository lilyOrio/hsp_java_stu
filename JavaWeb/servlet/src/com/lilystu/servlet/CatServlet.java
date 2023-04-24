package com.lilystu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CatServlet implements Servlet {
    private int count = 0;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        String method = servletRequest1.getMethod();
        count++;
        if (method.equals("GET")) {
            System.out.println("CatServlet GET 请求~~" + count);
        } else if (method.equals("POST")) {
            System.out.println("CatServlet POST 请求~~" + count);
        }
    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
