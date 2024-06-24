package com.lilystu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RequestAttributeController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        request.setAttribute("user", "lily");
        request.getSession().setAttribute("name", "jack");
        return "forward:ok";
    }

    @RequestMapping("/ok")
    @ResponseBody
    public String ok(@RequestAttribute("user") String userx,
                     @SessionAttribute("name") String namex,
                     HttpServletRequest request) {
        Object user = request.getAttribute("user");
        Object name = request.getSession().getAttribute("name");
        return "user-" + user + ", name-" + name + ",userx-" + userx + ",namex-" + namex;
        //user-lily, name-jack,userx-lily,namex-jack
    }
}
