package com.lilystu.springcloud.config;

import com.lilystu.springcloud.entity.Result;

public class CustomGlobalFallbackHandler {
    public static Result fallBackHandlerMethod1(Throwable e) {
        return Result.error("400", "java 异常信息= " + e.getMessage());
    }

    public static Result fallBackHandlerMethod2(Throwable e) {
        return Result.error("401", "java 异常信息= " + e.getMessage());
    }
}
