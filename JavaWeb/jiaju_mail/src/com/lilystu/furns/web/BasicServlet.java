package com.lilystu.furns.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 使用模板模式+动态绑定+反射调用 取代了频繁的if-else
 */
public abstract class BasicServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BasicServlet...");
        String action = req.getParameter("action");

        //使用反射 获取当前对象的方法
        //this 就是请求的Servlet对象实例
        //declaredMethod 方法对象 action名对应的方法
        try {
            Method declaredMethod = this.getClass().
                                     //参数含义：方法名，方法所需参数列表的Class对象（即参数类型）
                                     //一定要保证action的值要与方法名保持一致

                    getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //使用方法对象，进行反射调用
            declaredMethod.invoke(this,req,resp);//this：对象实例；req,resp：方法参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
