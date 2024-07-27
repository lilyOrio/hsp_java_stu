package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.service.MemberFeignService;
import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberConsumerFeignController {

    @Resource
    private MemberFeignService memberFeignService;

    @GetMapping(value = "/member/consumer/openfeign/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id) {
        return memberFeignService.getMemberById(id);
    }
}
