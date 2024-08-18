package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class StorageController {
    @Autowired
    private StorageService storageService;

    //扣减库存
    @PostMapping("/storage/reduce")
    public Result reduce(@RequestParam("productId") Long productId, @RequestParam("nums") Integer nums) {

        //模拟异常, 超时
        // openfeign 接口调用默认超时时间为 1s
//        try {
//            TimeUnit.SECONDS.sleep(12);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        storageService.reduce(productId, nums);
        log.info("reduce");
        return Result.success("200", "扣减库存成功 OK");
    }
}
