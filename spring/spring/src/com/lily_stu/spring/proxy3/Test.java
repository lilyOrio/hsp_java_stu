package com.lily_stu.spring.proxy3;

/**
 * 传统方式实现
 * 弊端：代码冗余过多
 */
public class Test {
    public static void main(String[] args) {
        SmartAnimalProxyProvider proxyProvider = new SmartAnimalProxyProvider(new SmartDog());
        SmartAnimal proxy = proxyProvider.getProxy();
        proxy.getSub(10,5);
    }
}
