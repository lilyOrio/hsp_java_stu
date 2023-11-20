package com.lilystu.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class SmartDog implements SmartAnimalable{
    public float getSum(float i, float j) {
        float result = i + j;
        System.out.println("getSum() 方法内部打印result= " + result);
        return result;
    }

    public float getSub(float i, float j) {
        float result = i - j;
        System.out.println("getSub() 方法内部打印result= " + result);
        return result;
    }
}
