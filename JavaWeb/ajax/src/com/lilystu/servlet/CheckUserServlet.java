package com.lilystu.servlet;

import com.google.gson.Gson;
import com.lilystu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/checkUserServlet")
public class CheckUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckUserServlet...");
        //接受ajax提交的数据
        String username = request.getParameter("username");
        System.out.println("uname = " + username);

        response.setContentType("text/html;charset=utf-8");
        if ("king".equals(username)){
            User king = new User(100, "king", "king@shou.com", "666");
            String strKing = new Gson().toJson(king);
            response.getWriter().write(strKing);
        }else {
            response.getWriter().write("");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
