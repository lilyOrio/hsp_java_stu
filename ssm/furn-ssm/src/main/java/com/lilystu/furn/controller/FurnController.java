package com.lilystu.furn.controller;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.bean.Msg;
import com.lilystu.furn.service.FurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FurnController {

    @Autowired(required = false)
    FurnService furnService;

    @PostMapping("/save")
    @ResponseBody//将返回数据转为json格式
    public Msg save(@RequestBody Furn furn) {//将前端发送的json 数据,转成JavaBean 数据
        furnService.save(furn);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping("/furns")
    public Msg listFurns() {
        List<Furn> furnList = furnService.findAll();
        return Msg.success().add("furnsList", furnList);
    }

    @PutMapping("/update")
    @ResponseBody
    public Msg update(@RequestBody Furn furn) {
        furnService.update(furn);
        return Msg.success();
    }
}
