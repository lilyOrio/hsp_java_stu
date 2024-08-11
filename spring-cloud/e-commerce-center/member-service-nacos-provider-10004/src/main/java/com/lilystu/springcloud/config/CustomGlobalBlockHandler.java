package com.lilystu.springcloud.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lilystu.springcloud.entity.Result;

public class CustomGlobalBlockHandler {
    public static Result handlerMethod1(BlockException exception) {
        return Result.error("400", "客户自定义异常处理 handlerm1()");
    }

    public static Result handlerMethod2(BlockException exception) {
        return Result.error("401", "客户自定义异常处理 handlerm2()");
    }
}
