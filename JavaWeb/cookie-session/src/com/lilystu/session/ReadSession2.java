package com.lilystu.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/readSession2")
public class ReadSession2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ReadSession2...");
        HttpSession session = request.getSession();
        System.out.println("Read2 sessionId = " + session.getId());
        Object email = session.getAttribute("job");
        if (email!=null){
            System.out.println("job = " + (String)email);
        }else {
            System.out.println("Session中不存在属性为job的值");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
