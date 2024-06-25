package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@Slf4j
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

    //响应一个注册请求
    // map、model封装注册数据
    @GetMapping("/register")
    public String register(Map<String, Object> map,
                           Model model,
                           HttpServletResponse response) {
        //map 和 model 封装的数据会存到request域里面
        map.put("user", "lily");
        map.put("job", "java");
        model.addAttribute("sal", 25000);

        //创建cookie并通过response添加到浏览器中
        response.addCookie(new Cookie("email", "@qq.com"));
        return "forward:registerOk";
    }

    @GetMapping("/registerOk")
    @ResponseBody
    public String registerOk(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "-" + cookie.getValue());
        }
        return "user - " + request.getAttribute("user") + ", job - " + request.getAttribute("job")
                + ", sal - " + request.getAttribute("sal");
        //user - lily, job - java, sal - 25000
    }

    @PostMapping("/save_monster")
    @ResponseBody
    public String saveMonster(Monster monster){
        log.info("monster{}",monster);
        return "monster = " + monster;
    }
}
