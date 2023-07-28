package com.lilystu.furns.web;

import com.lilystu.furns.entity.Member;
import com.lilystu.furns.service.MemberService;
import com.lilystu.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/adminServlet")
public class AdminServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member manager = memberService.login(new Member(null, username, password, null));
        if (manager != null){
            request.getSession().setAttribute("member",manager);
            request.getRequestDispatcher("/views/manage/manage_menu.jsp")
                    .forward(request,response);
        }else {
            //登录失败
            System.out.println(username +":"+password + " 登录失败!" );
            request.setCharacterEncoding("utf-8");
            request.setAttribute("errMsg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request, response);
        }
    }
}
