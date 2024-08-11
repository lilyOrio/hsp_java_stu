package com.lilystu.springcloud.controller;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import com.lilystu.springcloud.service.MemberOpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MemberNacosConsumerController {
    public static final String MEMBER_SERVICE_NACOS_PROVIDER_URL = "http://member-service-nacos-provider";

    @Resource
    private RestTemplate restTemplate;


    @Resource
    private MemberOpenFeignService memberOpenFeignService;

    @GetMapping("/member/openfeign/consumer/get/{id}")
    public Result<Member> getMemberOpenfeignById(@PathVariable("id") Long id) { //这里使用 Openfeign 接口方式远程调用服务
        System.out.println("通过 openfeignd+负载均衡 调用服务");
        return memberOpenFeignService.getMemberById(id);
    }


    @PostMapping("/member/nacos/consumer/save")
    public Result<Member> save(Member member) {
        //请求参数member 已经转换成 json格式，所以服务提供端需要添加@RequestBody 表示接收json格式的数据
        return restTemplate.postForObject(MEMBER_SERVICE_NACOS_PROVIDER_URL + "/member/save", member, Result.class);
    }

    @GetMapping("/member/nacos/consumer/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(MEMBER_SERVICE_NACOS_PROVIDER_URL + "/member/get/" + id, Result.class);
    }
}
