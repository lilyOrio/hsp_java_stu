package com.lilystu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpServletRequestMethods extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//通过HttpServletRequest对象获取表单提交信息
        /***********************************
         * 获取请求头消息
         ***********************************/
        System.out.println("1." + request.getRequestURI());
        System.out.println("2." + request.getRequestURL());
        System.out.println("3." + request.getRemoteAddr());//127.0.0.1
//      思考题：如发现某个ip 在10s 中，访问的次数超过100 次，就封ip
//      实现思路：1 用一个集合concurrentHashmap[ip-访问次数] 2[线程/定时扫描]3 做成处理

//      获取http 请求头的信息，可以指定其他，比如User-Agent , Host 等
//      getHeader("Host")
// 说明， 如果我们希望得到请求的头的相关信息， 可以使用request.getHeader("请求头字段")
        System.out.println("http 请求头HOST= " + request.getHeader("Host"));
        System.out.println("该请求的发起地址是= " + request.getHeader("Referer"));//可以通过这个拒绝掉非本应用的请求
// 请获取访问网站的浏览器是什么？
        String userAgent = request.getHeader("User-Agent");
        System.out.println("User-Agent= " + userAgent);
        String[] s = userAgent.split(" ");
        System.out.println("浏览器= " + s[s.length - 1].split("\\/")[0]);
        // 主要是Get / Post
        System.out.println("http 请求方式~= " + request.getMethod());
//        System.out.println(request.getRequestedSessionId());
        /***********************************
         * 获取和请求参数相关(表格数据)信息, 注意要求在返回数据前，获取参数
         ***********************************/
        //1. 获取表单的数据[单个数据]
        //username=tom&pwd=&hobby=hsp&hobby=spls
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username= " + username);
        System.out.println("pwd= " + pwd);
        response.setContentType("text/html;charset=utf-8");
//        response.setContentType("application/x-tar;charset=utf-8");//文件下载
        PrintWriter writer = response.getWriter();
        writer.println("username= " + username);
        writer.println("pwd= " + pwd);
        writer.flush();
        writer.close();

        //2. 获取表单一组数据
        String[] hobbies = request.getParameterValues("hobby");
        //增强for 循环的快捷键iter->回车即可, 能使用快捷键，就使用快捷键
        for (String hobby : hobbies) {
            System.out.println("hobby=" + hobby);
        }
        //推而广之, 如果是单选, 下拉框等等. => 作业布置
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
