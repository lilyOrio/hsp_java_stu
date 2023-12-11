package com.lily_stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserServlet {

    /**
     * 1. @RequestMapping("/login"): /login 等价于web.xml servlet 的url-pattern
     * 2. return "login_ok"
     *  2.1 将结果"login_ok" 返回给InternalResourceViewResolver
     *  2.2 然后跳转到哪个页面这里就是/WEB-INF/pages/login_ok.jsp
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        System.out.println("login ok....");
        return "login_ok";
    }

}
