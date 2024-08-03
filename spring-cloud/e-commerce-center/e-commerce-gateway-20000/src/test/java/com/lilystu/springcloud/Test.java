package com.lilystu.springcloud;

import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();//获取断言时间格式
        System.out.println(now);
    }
}
