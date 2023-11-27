package com.lilystu.spring.aop;

/**
 * 实现AOP
 * 要点，在后置处理器的after方法返回切入方法的代理对象
 */
public class SmartAnimalAspect {
    public static void showBeginLog() {
        System.out.println("前置通知");
    }
    public static void showSuccessEndLog() {
        System.out.println("返回通知");
    }
}
