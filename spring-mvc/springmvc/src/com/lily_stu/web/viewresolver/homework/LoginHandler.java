package com.lily_stu.web.viewresolver.homework;

import com.lily_stu.web.viewresolver.homework.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginHandler {

    @RequestMapping("/doLogin")
    public String doLogin(User user){
        if ("11".equals(user.getUsername()) && "22".equals(user.getPassword())){
            return "forward:/WEB-INF/pages/login_ok.jsp";
        }else {
            return "redirect:/homework/login_fail.jsp";
        }
    }
}
