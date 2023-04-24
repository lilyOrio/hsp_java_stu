package com.lilystu.servlet.annotation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注解方式配置Servlet
 * 根据注解源码了解可以配置的信息
 */
@WebServlet(urlPatterns = {"/ok1","/ok2"}/*,loadOnStartup = 1*/
        /*,initParams ={@WebInitParam(name = "aa",value = "bb"),
        @WebInitParam(name = "aa",value = "bb")}*/)
public class OkServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("OkServlet doGet~~~");
    }
}
