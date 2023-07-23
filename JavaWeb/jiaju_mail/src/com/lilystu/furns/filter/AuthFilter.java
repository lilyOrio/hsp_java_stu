package com.lilystu.furns.filter;

import com.google.gson.Gson;
import com.lilystu.furns.entity.Member;
import com.lilystu.furns.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于权限验证的过滤器
 * 如果登录过就放行
 * 未登录就返回登录界面
 *
 * @author lily
 * @version 1.0
 */
public class AuthFilter implements Filter {
    private List<String> excludedUrls;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludedUrls_ = filterConfig.getInitParameter("excludedUrls");
        String[] urls = excludedUrls_.split(",");
        excludedUrls = Arrays.asList(urls);
        for (String url : urls) {
            System.out.println(url);
//            excludedUrls.add(url);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//得到请求URL
        String url = request.getServletPath();
        System.out.println("url=" + url);

        if (!excludedUrls.contains(url)) {
//        得到登录页面的member对象
            Member member = (Member) request.getSession().getAttribute("member");
            if (member == null) {
//              判断 是否为ajax请求
                if (!WebUtils.isAjaxRequest(request)){
                    System.out.println("member is null");
//            请求转发不走过滤器
                    request.getRequestDispatcher("/views/member/login.jsp")
                            .forward(request, servletResponse);
                }else {
                    Map<String, Object> resultMap = new HashMap<>();
//                    返回URL
                    resultMap.put("url", "views/member/login.jsp");
                    String resultJson = new Gson().toJson(resultMap);
                    //返回json数据
                    response.getWriter().write(resultJson);
                }
                return;
            }
        }
//        用户不为空继续访问
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
