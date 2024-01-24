package com.lily_stu.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor01 implements HandlerInterceptor {
/**
 * 1.pre 方法在目标方法执行之前被调用.
 * 2.返回false, 则不会再执行目标方法. 可以在此响应请求返回给页面
 * 3.不管返回true 还是false
 * 会执行当前拦截器之前的拦截器的afterCompletion 方法. 注意:不会执行当前拦截器的afterCompletion 方法.
 */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===MyInterceptor01 preHandle()===");
        return true;
    }

    /**
     * 说明在目标方法被执行之后执行. 可以在方法中访问到目标方法返回的ModelAndView 对象.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("===MyInterceptor01 postHandle()===");
    }

    /**
     * 若preHandle 返回true, 则方法在渲染视图之后被执行.
     * 若preHandle 返回false, 则该方法不会被调用.
     * 若当前拦截器的下一个拦截器的preHandle 方法返回false, 则在执行下一个拦截器preHandle 方法后马上被执行.
     * 可以访问到目标方法中出现的异常.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("===MyInterceptor01 afterCompletion()===");
    }
}
