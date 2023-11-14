package com.lily_stu.spring.proxy2;

/**
 * 传统方式实现
 * 弊端：代码冗余过多
 */
public class Test {
    public static void main(String[] args) {
        Vehicle vehicle = new Car();//可以切换成new Ship()
        vehicle.run();
    }
}
