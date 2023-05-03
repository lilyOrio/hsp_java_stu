package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lily
 * @version 1.0
 */
@WebServlet(urlPatterns = "/ComputerServlet")
public class ComputerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String header = request.getHeader("User-Agent");
//        System.out.println(split[0]+" "+split[1]);
//        正则表达式
        String regStr = "\\((.*)\\)";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(header);
        while (matcher.find()){
            String group = matcher.group(0);// (Windows NT 10.0; Win64; x64; rv:97.0)
            System.out.println(group);
            String group1 = matcher.group(1);// Windows NT 10.0; Win64
            String[] operInfos = group1.split(";");
            for (String s : operInfos) {
                System.out.println(s);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
