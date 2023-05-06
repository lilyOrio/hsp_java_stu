package com.lilystu.tomcat.servlet;

import com.lilystu.tomcat.http.LilyRequest;
import com.lilystu.tomcat.http.LilyResponse;

import java.io.IOException;

/**
 * Servlet接口规范
 * 展示三个核心方法
 */
public interface LilyServlet {
    void init() throws Exception;
    void service(LilyRequest var1, LilyResponse var2) throws IOException;
    void destroy();
}
