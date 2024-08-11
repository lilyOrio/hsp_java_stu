package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "member-service-nacos-provider",
        fallback = MemberFeignFallbackService.class)
public interface MemberOpenFeignService {
    @GetMapping(value = "/member/get/{id}")
    public Result<Member> getMemberById(@PathVariable("id") Long id);
}
