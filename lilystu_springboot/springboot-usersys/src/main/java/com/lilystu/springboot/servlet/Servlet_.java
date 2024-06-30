package com.lilystu.springboot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(urlPatterns = {"/servlet01","/servlet02"})
public class Servlet_ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Servlet_ doGet");
        resp.getWriter().write("hi~,Servlet_");
    }

    /*
    自定义servlet不经过拦截器，拦截器是 DispatcherServlet(urlPatterns="/") 绑定的spring容器里面的组件，
    Tomcat的请求路径uri遵循精准匹配原则，请求url是/servlet01,/servlet02，会先匹配到这个servlet，而不是SpringBoot自动配置的DispatcherServlet
     */
}
