package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.service.FurnService;
import com.lilystu.springboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FurnController {

    @Autowired
    FurnService furnService;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Furn furn) {
        furnService.save(furn);
        return Result.success();
    }
}
