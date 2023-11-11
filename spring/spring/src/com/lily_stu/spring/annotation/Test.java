package com.lily_stu.spring.annotation;

public class Test {
    public static void main(String[] args) throws Exception {
        LilySpringApplicationContext ioc =
                new LilySpringApplicationContext(LilySpringConfig.class);
        System.out.println("ok~");
        System.out.println(ioc.getBean("myComponent"));
        System.out.println(ioc.getBean("userAction"));
        System.out.println(ioc.getBean("lilyUserDAO"));
        System.out.println(ioc.getBean("userService"));
    }
}
