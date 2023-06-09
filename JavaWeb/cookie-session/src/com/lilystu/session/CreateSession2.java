package com.lilystu.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 演示Session生命周期
 * 定义：Session的生命周期时长值得是两次操作session对象的最大时间间隔，
 *      在有效时间内访问则会重新计时
 */
@WebServlet(urlPatterns = "/createSession2")
public class CreateSession2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CreateSession2...");
        HttpSession session = request.getSession();
        String id = session.getId();
        System.out.println("Create2 sessionId = " + id);
        session.setMaxInactiveInterval(30);
        session.setAttribute("job","android");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
