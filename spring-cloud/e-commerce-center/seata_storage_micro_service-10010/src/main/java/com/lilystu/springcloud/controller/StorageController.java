package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StorageController {
    @Autowired
    private StorageService storageService;

    //扣减库存
    @PostMapping("/storage/reduce")
    public Result reduce(Long productId, Integer nums) {
        storageService.reduce(productId, nums);
        return Result.success("扣减库存成功 ok", null);
    }
}
