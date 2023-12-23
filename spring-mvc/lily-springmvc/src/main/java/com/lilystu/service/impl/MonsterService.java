package com.lilystu.service.impl;

import com.lilystu.entity.Monster;
import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.RequestMapping;
import com.lilystu.lilyspringmvc.annotation.Service;
import com.lilystu.service.IMonsterService;
import com.lilystu.service.ITest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonsterService implements IMonsterService, ITest {

    public void hi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>hi~</h1>");
    }

    @Override
    public List<Monster> listMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100, "牛魔王", "芭蕉扇", 500));
        monsters.add(new Monster(200, "蜘蛛精", "吐口水", 200));
        return monsters;
    }
}
