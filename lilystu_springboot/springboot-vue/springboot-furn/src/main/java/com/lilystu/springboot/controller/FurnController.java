package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.service.FurnService;
import com.lilystu.springboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FurnController {

    @Autowired
    FurnService furnService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Furn furn) {
        furnService.save(furn);
        return Result.success();
    }

    @RequestMapping("/furns")
    public Result<?> listFurns() {
        List<Furn> furnList = furnService.list();
        return Result.success(furnList);
    }

    //GET 获取 、POST 创建、PUT 更新、DELETE 删除
    @PutMapping("/update")
    public Result<?> update(@RequestBody Furn furn) {
        boolean b = furnService.updateById(furn);
        System.out.println(b);
        return Result.success();
    }
}
