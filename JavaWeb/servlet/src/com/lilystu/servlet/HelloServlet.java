package com.lilystu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */

/**
 * 1. 开发一个Servlet 需要 实现Servlet接口
 * 2. 实现Servlet接口的方法5个
 */
public class HelloServlet implements Servlet {

    private int count = 0;

    /**
     * 1.初始化 servlet
     * 2.当创建HelloServlet 实例时，会调用init方法
     * 3. 该方法只会被调用一次
     *
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init()~~~");
    }

    /**
     * 返回ServletConfig 也就是返回Servlet的配置
     *
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 1. service方法处理浏览器的请求(包括get/post)
     * 2. 当浏览器每次请求Servlet时，就会调用一次service
     * 3. 当tomcat调用该方法时，会把http请求的数据封装成实现ServletRequest接口的request对象
     * 4. 通过servletRequest 对象，可以得到用户提交的数据
     * 5. servletResponse 对象可以用于返回数据给tomcat->浏览器
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws
            ServletException, IOException {
//        如果count一直累计加一，说明HelloServlet是单例的，不同浏览器访问也是会累计加1的
        System.out.println("hi servlet~~~ " + count++);
//        浏览器每次请求都会开一个新的线程（tomcat每处理一次http请求都会为其新建一个请求）
        System.out.println("线程id："+Thread.currentThread().getName());

        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        String method = servletRequest1.getMethod();
        if (method.equals("GET")){
            doGet();
        }else if (method.equals("POST")){
            doPost();
        }
    }

    /**
     * 响应get请求
     */
    public void doGet(){
        System.out.println("doGet被调用~");
    }

    /**
     * 响应post请求
     */
    public void doPost(){
        System.out.println("doPost被调用~");
    }

    /**
     * 返回servlet信息，使用较少
     *
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 1. 该方法是在servlet销毁时，被调用
     * 2. 只会调用一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy~~");
    }
}
