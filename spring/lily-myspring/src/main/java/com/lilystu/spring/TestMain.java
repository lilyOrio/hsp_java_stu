package com.lilystu.spring;

import com.lilystu.spring.ioc.LilySpringApplicationContext;
import com.lilystu.spring.ioc.LilySpringConfig;

public class TestMain {
    public static void main(String[] args) throws Exception {
        LilySpringApplicationContext ioc = new LilySpringApplicationContext(LilySpringConfig.class);
        System.out.println("Ok~");
    }
}
