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


    //流量控制实例--关联

    /**
     * 当调用 member-service-nacos-provider-10004 的 /t2 API 接口时，如果 QPS 超过 1，
     * 这 时调用 /t1 API 接口 直接接失败，抛异常. 老师梳理 /t2 是关联的资源 , 限流的资源是 /t1
     * @return
     */
    @GetMapping(value = "/t1")
    public Result t1() {
        return Result.success("t1 执行");
    }

    @GetMapping(value = "/t2")
    public Result t2() {
        return Result.success("t2 执行");
    }

    //流量控制--warm up
    /**
     * 通常冷启动的过程系统允许通过的 QPS 曲线图(上图)
     *  默认 coldFactor 为 3，即请求 QPS 从 threshold / 3 开始，经预热时长逐渐升至设 定的 QPS 阈值
     *  这里的threshold 就是最终要达到的QPS阈值.
     */

    //流量控制--排队 削峰添谷
    /**
     * 1. 需求: 通过 Sentinel 实现 流量控制-排队
     * 2. 调用 member-service-nacos-provider-10004 的 /t3 API 接口，将 QPS 设置为 1
     * 3. 当调用 /t3 的 QPS 超过 1 时，不拒绝请求，而是排队等待, 依次执行
     * 4. 当等待时间超过 10 秒，则为等待超时.
     */
    @GetMapping(value = "/t3")
    public Result t3() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("enter getMemberById... 当 前 线 程 id=" +
                Thread.currentThread().getId() + " 时间=" + new Date());
        return Result.success("t3 执行");
    }
}
