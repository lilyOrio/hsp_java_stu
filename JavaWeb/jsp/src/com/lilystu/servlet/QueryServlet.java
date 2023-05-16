package com.lilystu.servlet;

import com.lilystu.entity.Monster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/queryServlet")
public class QueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("QueryServlet...");
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"小妖怪","巡山"));
        monsters.add(new Monster(200,"大妖怪","做饭"));
        monsters.add(new Monster(300,"老妖怪","打扫"));
        request.setAttribute("monsters",monsters);
        request.getRequestDispatcher("/homework/show.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
