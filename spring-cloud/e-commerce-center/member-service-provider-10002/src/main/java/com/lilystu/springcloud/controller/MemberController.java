package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MemberController {
    @Resource
    private MemberService memberService;

    @PostMapping(value = "/member/save")
    public Result save(@RequestBody Member member) {
        int result = memberService.save(member);
        log.info("reset= " + result);
        if (result > 0) { //成功
            return Result.success("添加用户成功 member-service-provider-10002", result);
        } else {
            return Result.error("401", "添加用户失败");
        }
    }

    @GetMapping(value = "/member/get/{id}")
    public Result getMemberById(@PathVariable("id") Long id) {
        Member member = memberService.queryMemberById(id);
        log.info("查询结果= " + member);
        if (member != null) {
            return Result.success("查询成功 member-service-provider-10002", member);
        } else {
            return Result.error("402", "ID= " + id + " 不存在");
        }
    }
}
