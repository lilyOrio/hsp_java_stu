package com.lilystu.furn.controller;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.bean.Msg;
import com.lilystu.furn.service.FurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
