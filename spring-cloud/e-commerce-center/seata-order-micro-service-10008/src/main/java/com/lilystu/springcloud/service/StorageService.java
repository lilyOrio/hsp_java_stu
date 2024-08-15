package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-micor-service")
public interface StorageService {
    @PostMapping(value = "/storage/reduce")
    Result reduce(@RequestParam("productId") Long productId, @RequestParam("nums") Integer nums);
}
