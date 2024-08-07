package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
            return Result.success("添加用户成功 member-service-nacos-provider-10004", result);
        } else {
            return Result.error("401", "添加用户失败");
        }
    }

    @GetMapping(value = "/member/get/{id}")
    public Result getMemberById(@PathVariable("id") Long id, HttpServletRequest request) {
//    @GetMapping(value = "/member/get",params = "id")
//    public Result getMemberById(Long id, HttpServletRequest request) {//流控规则

        //验证GateWayFilter
//        String address = request.getParameter("address");
//        String age = request.getParameter("age");
//        log.info("address={},age={}",address,age);

        //(1)模拟超时，这里暂停 1 秒 (2)流控规则,工作线程数限制1个
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //输出线程情况,一个请求开启一个线程
        System.out.println("enter getMemberById... 当 前 线 程 id=" +
                Thread.currentThread().getId() + " 时间=" + new Date());

        Member member = memberService.queryMemberById(id);
        log.info("查询结果= " + member);
        if (member != null) {
            return Result.success("查询成功 member-service-nacos-provider-10004", member);
        } else {
            return Result.error("402", "ID= " + id + " 不存在");
        }
    }
}
