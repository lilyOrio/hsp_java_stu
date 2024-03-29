package com.lilystu.controller;

import com.lilystu.entity.Monster;
import com.lilystu.lilyspringmvc.annotation.*;
import com.lilystu.service.IMonsterService;
import com.lilystu.service.ITest;
import com.lilystu.service.impl.MonsterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MonsterController {

    @AutoWired
    private IMonsterService monsterService;

    @AutoWired
    private ITest monsterService2;

    @RequestMapping("/monster/list")
    public void listMonsters(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(monsterService);
        System.out.println(monsterService2);
        try {
            List<Monster> monsters = monsterService.listMonsters();
            StringBuilder content = new StringBuilder("<h1>妖怪列表</h1>");
            content.append("<table width='500px' style='border-collapse: collapse' border='1px'>");
            for (Monster monster : monsters) {
                content.append("<tr><td>" + monster.getId() + "</td><td>"
                        + monster.getName() + "</td><td>" + monster.getSkill()
                        + "</td></tr>");
            }
            content.append("</table>");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/monster/find")
    public void findMonsters(HttpServletRequest request, HttpServletResponse response,
                             String name) {
        response.setContentType("text/html;charset=utf-8");
        try {
            System.out.println("妖怪查找~：" + name);
            List<Monster> monsters = monsterService.findMonstersByName(name);
            StringBuilder content = new StringBuilder("<h1>你找到的妖怪列表</h1>");
            content.append("<table width='400px' style='border-collapse: collapse' border='1px'>");
            for (Monster monster : monsters) {
                content.append("<tr><td>" + monster.getId() + "</td><td>"
                        + monster.getName() + "</td><td>" + monster.getSkill()
                        + "</td></tr>");
            }
            content.append("</table>");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/monster/login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response, String mName) {
        boolean b = monsterService.login(mName);
        System.out.println("mName = " + mName);
        request.setAttribute("mName",mName);
        if (b) {
            //重定向
            return "redirect:/login_ok.jsp";
            //默认是转发
//            return "/login_ok.jsp";
            //转发
//            return "forward:/login_ok.jsp";
        } else {
            return "forward:/login_error.jsp";
        }
    }

    @RequestMapping(value = "/monster/list/json")
    @ResponseBody
    public List<Monster> listMonstersByJson(HttpServletRequest request,
                                            HttpServletResponse response) {
        List<Monster> monsters = monsterService.listMonsters();
        return monsters;
    }
}
