package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-account-micor-service")
public interface AccountService {
    @PostMapping(value = "/account/reduce")
    Result reduce(@RequestParam("userId") Long userId, @RequestParam("money") Integer money);
}