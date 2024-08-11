package com.lilystu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lilystu.springcloud.config.CustomGlobalBlockHandler;
import com.lilystu.springcloud.config.CustomGlobalFallbackHandler;
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
    private static int num;

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
     *
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

    /**
     *熔断降级实例-异常比例
     * 1. 需求: 通过 Sentinel 实现 熔断降级控制
     * 2. 当调用 member-service-nacos-provider-10004 的 /t4 API 接口时，当资源的每秒请求
     *  量>=5，并且每秒异常总数占通过量的比值超过 20%(即异常比例到 20%), 断路器打开(即: 进入降级状态),
     *  让 /t4 API 接口 微服务不可用
     * 3. 当对/t4 API 接口 访问降到 1S 内 1 个请求，降低访问量了，断路器关闭，5 秒后微服 务恢复
     *
     * 慢查询\异常数\异常比例
     */


    /**
     * 1. 需求: 通过 Sentinel 实现 热点 Key 限流
     * 2. 对 member-service-nacos-provider-10004 的 /news?id=x&type=x API 接口进行热点限 流
     * 3. 假定 id=10 这一条新闻是当前的热点新闻， 当查询新闻时，对通常的 id(非热点新闻) 请求 QPS 限定为 2,
     * 如果 id=10 QPS 限定为 100
     * 4. 如果访问超出了规定的 QPS, 触发热点限流机制, 调用自定义的方法，给出提示信息.
     * 5. 当对 /news?id=x&type=x API 接口 降低访问量，QPS 达到规定范围, 服务恢复
     */
    //热点 key 限制异常处理方法
    public Result newsBlockHandler(String id, String type, BlockException exception) {
        return Result.success("查询 id= " + id + " 新闻 触发热点 key 限制保护.. sorry");
    }

    /*** 注意 @GetMapping 是 url 带有 / ,
     * @SentinelResource 的 value 是没有 /的
     */
    @GetMapping("/news")
    @SentinelResource(value = "news", blockHandler = "newsBlockHandler")
    public Result queryNews(@RequestParam(value = "id", required = false) String id,
                            @RequestParam(value = "type", required = false) String type) {
        log.info("到 DB 查询数据库.... ");
        return Result.success("返回 id= " + id + " 新闻 fromDB");
    }

    /*** 老韩解读 * value = "t6": SentinelResource 资源名
     * * blockHandlerClass = CustomGlobalBlockHandler.class: 全局限流处理类
     * * blockHandler = "handlerMethod1": 全局限流处理类的哪个方法,可以指定.
     * */
    @GetMapping(value = "/t6")
    @SentinelResource(value = "t6",
            fallbackClass = CustomGlobalFallbackHandler.class,
            fallback = "fallBackHandlerMethod1",
            blockHandlerClass = CustomGlobalBlockHandler.class,
            blockHandler = "handlerMethod1",
            exceptionsToIgnore = {NullPointerException.class})
    public Result t6() {
        //假定: 当访问 t6 资源次数是 5 的倍数时，就出现了一个 java 的异常
        if (++num % 5 == 0) {
            throw new NullPointerException("null 的值异常 num= " + num);
        }

        if (num % 6 == 0) {
            throw new RuntimeException("num 的值异常 num= " + num);
        }

        log.info("执行 t6() 线程 id= " + Thread.currentThread().getId());
        return Result.success("200", "t6()执行成功");
    }
}
