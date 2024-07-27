package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component //这里 MEMBER-SERVICE-PROVIDER 就是 Eureka Server 服务提供方注册的名称
@FeignClient(value = "MEMBER-SERVICE-PROVIDER")
public interface MemberFeignService {
    /*** 老韩解读
     * * 1. 远程调用的方式为 get
     * * 2. 远程调用的 url 为 http://MEMBER-SERVICE-PROVIDER/member/get/{id}
     * * 3. MEMBER-SERVICE-PROVIDER 是 注册中心服务 * member-service-provider:10000/10002
     * * 4. 会根据 OpenFeign 的均衡算法来决定是调用 10000 还是 10002 */
    @GetMapping(value = "/member/get/{id}")//这个uri需要与服务提供端的目标uri一致
    public Result<Member> getMemberById(@PathVariable("id") Long id);
}
