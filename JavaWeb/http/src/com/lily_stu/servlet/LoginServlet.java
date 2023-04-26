package com.lily_stu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet POST!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet GET!");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter responseWriter = response.getWriter();//给浏览器回数据
        responseWriter.println("登录成功！");
//        为了确保数据返回
        responseWriter.flush();//表示将缓存的数据进行刷新
        responseWriter.close();//表示关闭流，及时释放资源
    }
}
