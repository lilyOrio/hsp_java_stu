package com.lilystu.springcloud.service;

import com.lilystu.springcloud.entity.Member;
import com.lilystu.springcloud.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class MemberFeignFallbackService implements MemberOpenFeignService{
    @Override
    public Result<Member> getMemberById(Long id) {
        return Result.error("500", "被调用服务异常, 熔断降级，快速返回结果, 防止线程堆 积");
    }
}
