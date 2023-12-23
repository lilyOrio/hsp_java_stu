package com.lilystu.controller;

import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class MonsterController {

    @RequestMapping("/monster/list")
    public void listMonsters(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>妖怪列表</h1>");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
