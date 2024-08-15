package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {
    @Resource
    AccountService accountService;

    /*** 扣减账户余额 */
    @PostMapping("/account/reduce")
    public Result result(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {
        accountService.reduce(userId, money);
        return Result.success("200", "扣减账户余额 OK");
    }
}
