package com.lilystu.furns.web;

import com.lilystu.furns.entity.Member;
import com.lilystu.furns.service.MemberService;
import com.lilystu.furns.service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/memberServlet")
public class MemberServlet extends BasicServlet {
    private MemberService memberService = new MemberServiceImpl();

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("MemberServlet...");
//        String action = request.getParameter("action");
//        System.out.println("action=" + action);
//        if ("login".equals(action)) {
//            login(request, response);
//        } else if ("register".equals(action)) {
//            register(request, response);
//        } else {
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("请求参数 action 错误！");
//        }
//    }

    /**
     * 安全退出,重定向到首页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //销毁当前用户session
        session.invalidate();
        response.sendRedirect(getServletContext().getContextPath());
    }

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("MemberServlet==login==");
        //接受用户注册信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member member = memberService.login(new Member(null, username, password, null));
        if (member != null) {
            //登录成功
            System.out.println(username + "登录成功!");
            request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request, response);
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        } else {
            //登录失败
            System.out.println(username + "登录失败!");
            request.setCharacterEncoding("utf-8");
            request.setAttribute("errMsg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
        }

    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberServlet==register==");
        //接受用户注册信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //判断用户名是否可用
        if (memberService.isExistsUsername(username)) {
            //用户已存在，后面可以添加提示信息
            request.getRequestDispatcher("/views/member/login.jsp")
                    .forward(request, response);
        } else {
            //注册用户
            if (memberService.register(new Member(null, username, password, email))) {
                request.getRequestDispatcher("/views/member/register_ok.html")
                        .forward(request, response);
            } else {
                request.getRequestDispatcher("/views/member/register_fail.html")
                        .forward(request, response);
            }
        }
    }
}
