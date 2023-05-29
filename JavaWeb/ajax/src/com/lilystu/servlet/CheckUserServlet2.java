package com.lilystu.servlet;

import com.google.gson.Gson;
import com.lilystu.entity.User;
import com.lilystu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/checkUserServlet2")
public class CheckUserServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckUserServlet2 ...");
        //接受jQuery发送的ajax数据
        String username = request.getParameter("username");
        response.setContentType("text/json,charset=utf8");
        User userByName = new UserService().getUserByName(username);
        if (userByName != null){
            response.getWriter().write(new Gson().toJson(userByName));
            return;
        }else {
            User user = new User(-1, "", "", "");
            String strUser = new Gson().toJson(user);
            response.getWriter().write(strUser);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
