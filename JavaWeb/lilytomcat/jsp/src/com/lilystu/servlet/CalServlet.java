package com.lilystu.servlet;

import com.lilystu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/calServlet")
public class CalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CalServlet...");
        int num1 = WebUtils.parseInt(request.getParameter("num1"), 0);
        int num2 = WebUtils.parseInt(request.getParameter("num2"), 0);
        String oper = request.getParameter("oper");
        double res = 0;
        switch (oper) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "x":
                res = num1 * num2;
                break;
            case "÷":
                res = (double)num1 / num2;
                break;
            default:
                System.out.println(oper + "运算符格式不正确！");
                break;
        }
        String resInfo = String.format("%s %s %s = %s", num1, oper, num2, res);
        request.setAttribute("res", resInfo);
        System.out.println(resInfo);
        request.getRequestDispatcher("/cal/calRes.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
