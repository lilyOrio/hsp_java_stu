package com.lily_stu.spring.proxy2;

public class Car implements Vehicle {
    @Override
    public void run() {
//        System.out.println("交通工具开始运行了...");
        System.out.println("小汽车在公路running..");
//        System.out.println("交通工具停止运行了...");
    }

    @Override
    public String fly(int height) {
        System.out.println("小汽车可以飞的高度：" + height);
        return "小汽车可以飞的高度：" + height;
    }
}
