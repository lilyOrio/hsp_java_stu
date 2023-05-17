package com.lilystu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter被Tomcat创建时调用此方法，可以完成一些初始化动作
        System.out.println("ManagerFilter init被调用。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
//      每次调用该filter时，doFilter会被调用
        System.out.println("ManagerFilter doFilter被调用。。。");
//        如果这里没有调用继续请求的方法，则会在这里停止

//        如果继续访问目标资源==》
//        这里的request和response对象已经被Tomcat封装好了
        filterChain.doFilter(servletRequest,servletResponse);//等价于放行
    }

    @Override
    public void destroy() {

    }
}
