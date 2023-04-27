package com.lilystu.servlet.servlet_context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletContext_ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取web.xml 中配置的上下文参数context-param
//        获取ServletContext对象
        ServletContext context = this.getServletContext();
        String website = context.getInitParameter("website");
        String company = context.getInitParameter("company");
        System.out.println(website + "\n" + company);
//        获取当前的工程路径，格式: /工程路径
        String contextPath = context.getContextPath();
        System.out.println("contextPath:" + contextPath);
//        获取工程部署后在服务器硬盘上的绝对路径
//        "/"表示项目发布后的根路径
        String realPath = context.getRealPath("/");
        System.out.println("realPath:" + realPath);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
