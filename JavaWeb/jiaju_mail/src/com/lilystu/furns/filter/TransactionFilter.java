package com.lilystu.furns.filter;

import com.lilystu.furns.entity.Member;
import com.lilystu.furns.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 控制事务的过滤器
 *
 * @author lily
 * @version 1.0
 */
public class TransactionFilter implements Filter {
    private List<String> excludedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            //走完之后统一提交
            JDBCUtilsByDruid.commit();
        } catch (IOException e) {
            //只有try{}中出现了异常才会回滚
            //问题一分析：BasicServlet中捕获了异常，这里的try{}中就不会异常
            //小收货：异常也是可以参与业务逻辑的
            JDBCUtilsByDruid.rollback();
        }
    }

    @Override
    public void destroy() {

    }
}
