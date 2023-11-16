package com.lily_stu.spring.aop.xml;

public class SmartDog implements SmartAnimal {
    @Override
    public float getSum(float i, float j) {
        float result = i + j;
        System.out.println("getSum() 方法内部打印result= " + result);
        return result;
    }

    @Override
    public float getSub(float i, float j) {
        float result = i - j;
//        result = 1/0;//模拟算数异常
        System.out.println("getSub() 方法内部打印result= " + result);
        return result;
    }
}
