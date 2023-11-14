package com.lily_stu.spring.proxy3;

public class SmartDog implements SmartAnimal {
    @Override
    public int getSum(int a, int b) {
        System.out.println("result = " + (a + b));
        return a + b;
    }

    @Override
    public int getSub(int a, int b) {
        System.out.println("result = " + (a - b));
        return a - b;
    }
}
