package com.lilystu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DBServlet extends HttpServlet {

//    ServletContext
    //ServletConfig对象创建流程：
    //1.当Servlet对象时，tomcat会同时创建一个ServletConfig对象
    //2.如果Servlet的init方法调用super.init()
    //   public void init(ServletConfig config) throws ServletException {
    //        this.config = config;
    //        this.init();
    //        }
    //	把tomcat创建的ServletConfig对象赋给GenericServlet的属性（private transient ServletConfig config;）
    //	这样的话getServletConfig()就可以获取到该ServletConfig对象
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init servletConfig:" + servletConfig);//和doPost的servletConfig是同一个对象
        super.init(servletConfig);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        System.out.println("doPost servletConfig:" + servletConfig);
        String username = servletConfig.getInitParameter("username");
        String pwd = servletConfig.getInitParameter("pwd");
        System.out.println("username:" + username + " " + "pwd:" + pwd);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
