package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HiServlet extends HttpServlet {
    /*
    浏览器访问时会调用HttpServlet的service()方法，该方法内可以调用this.doGet方法等等，根据动态绑定机制，
    执行时调用的是HiServlet重写的doGet方法。

    注意：如果HiServlet重写了service方法，而重写的方法里没有调用doGet方法，那么下面从写的doGet方法不会被执行（调用）到

    这个知识点涉及动态绑定，验证动态绑定参考test包下的代码。
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       默认是GET方法
        System.out.println("doGet~~");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost~~");
    }
}
