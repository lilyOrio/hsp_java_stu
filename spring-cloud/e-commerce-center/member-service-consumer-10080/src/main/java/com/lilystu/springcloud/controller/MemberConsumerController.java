package com.lilystu.springcloud.controller;


import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class MemberConsumerController {

    //定义一个基础url地址
    /*
    1. MEMBER-SERVICE-PROVIDER 就是服务提供方[集群]注册到 EurekaServer 的名称
    2. 也就是服务提供方[集群] 对外暴露的名称为 MEMBER-SERVICE-PROVIDER
    3. MEMBER-SERVICE-PROVIDER 目 前 有 两 个 Availability Zones 1member-service-provider:10002 , member-service-provider:10000
     */
    public static final String MEMBER_SERVICE_PROVIDER_URL = "http://MEMBER-SERVICE-PROVIDER";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/member/consumer/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service name = {}", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(instance.getServiceId() + "\t" + instance.getHost()
                        + "\t" + instance.getPort() + "\t" + instance.getUri());
            }
        }
        return discoveryClient;
    }

    @PostMapping("/member/consumer/save")
    public Result<Member> save(Member member) {

        /* 老韩解读
         * 1. MEMBER_SERVICE_PROVIDER_URL + "/member/save" 请求的 url
         * * 2. member 请求参数
         * * 3. Result.class http 响应被转换的对象类型
         * */

        //请求参数member 已经转换成 json格式，所以服务提供端需要添加@RequestBody 表示接收json格式的数据
        return restTemplate.postForObject(MEMBER_SERVICE_PROVIDER_URL + "/member/save", member, Result.class);
    }

    @GetMapping("/member/consumer/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(MEMBER_SERVICE_PROVIDER_URL + "/member/get/" + id, Result.class);
    }
}
