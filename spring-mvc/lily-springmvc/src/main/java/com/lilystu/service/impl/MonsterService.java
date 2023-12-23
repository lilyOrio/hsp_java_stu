package com.lilystu.service.impl;

import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.RequestMapping;
import com.lilystu.service.IMonsterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MonsterService implements IMonsterService {

    @RequestMapping("/hi/service")
    public void hi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hi~</h1>");
    }
}
