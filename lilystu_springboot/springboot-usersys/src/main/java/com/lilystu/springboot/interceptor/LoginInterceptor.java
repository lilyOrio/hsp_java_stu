package com.lilystu.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    //目标方法前调用
    //请求转发也会被拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        log.info("preHandle拦截器请求的uri：{}",requestURI);
        log.info("preHandle拦截器请求的url：{}",requestURL);
        //进行登录校验
        Object adminLogin = request.getSession().getAttribute("loginAdmin");
        //用户登录成功
        if (adminLogin != null){
            return true;
        }
        //用户未登录拦截
        request.setAttribute("msg","请登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    @Override
    //目标方法执行后调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle 执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion 执行");
    }


    /*
    1.URI 和 URL
        URI：标识符 站内唯一标识资源
        URL：定位器 网络查找资源的定位器

        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();

        : preHandle拦截器请求的uri：/manage.html
        : preHandle拦截器请求的url：http://localhost:10001/manage.html

    2.拦截器和过滤器
        -过滤器实现的是javax.servlet.Filter接口，其使用需要依赖于Tomcat等容器，filter只能在web程序中使用
        -拦截器Interceptor 是一个Spring组件，由Spring容器管理，不依赖Tomcat可以单独使用
        一条请求过来-->Tomcat-->Filter-->Servlet-->Interceptor-->Controller
        debug演示
        -过滤器不会处理请求转发，拦截器会处理请求转发
     */
}
