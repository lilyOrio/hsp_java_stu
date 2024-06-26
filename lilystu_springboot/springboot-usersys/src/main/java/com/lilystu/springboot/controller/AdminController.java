package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Admin;
import com.lilystu.springboot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    //响应用户登录请求
    @PostMapping("/login")
    //Model model 可以添加属性值到request域中
    public String login(Admin admin, HttpSession session, Model model) {
        String name = admin.getName();
        String pwd = admin.getPwd();
        if (StringUtils.hasText(name) && "666".equals(pwd)) {
            //将用户添加到session
            session.setAttribute("loginAdmin", admin);
            //验证通过，重定向到manage.html
            //使用重定向是为了避免重复提交
            return "redirect:/manage.html";
        } else {
            //验证不通过，返回到登录页面,默认请求转发
            model.addAttribute("msg","账号或密码错误！！");
            return "adminLogin";
        }
    }

    @GetMapping("/manage.html")
    public String mainPage(Model model, HttpSession session) {
        Object loginAdmin = session.getAttribute("loginAdmin");
        if (loginAdmin == null) {
            //禁止非法登录
            model.addAttribute("msg","请登录！");
            return "adminLogin";
        }
        List<User> users = new ArrayList<>();
        users.add(new User(1, "mary", "66666", 20, "@qq.com"));
        users.add(new User(2, "jack", "66666", 20, "@qq.com"));
        users.add(new User(3, "马超", "66666", 20, "@qq.com"));
        users.add(new User(4, "鲁班", "66666", 20, "@qq.com"));
        users.add(new User(5, "阿波罗", "66666", 20, "@qq.com"));
        //将数据放入request域中
        model.addAttribute("users", users);
        return "manage";
    }
}
