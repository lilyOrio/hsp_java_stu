package com.lilystu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //表示这个类是一个切面类
@Component //需要加入到IOC 容器
public class SmartAnimalAspect {

    //切入点表达式重用
    @Pointcut(value = "execution(public float com.lilystu.spring.aop.SmartDog.getSub(float,float))")
    public void myPointCut() {
    }

    @Before(value = "myPointCut()")
    public void showBegin(JoinPoint joinPoint) {
        System.out.println("前置通知");
    }

    @AfterReturning(value = "myPointCut()", returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        System.out.println("返回通知");
    }

    @AfterThrowing(value = "myPointCut()", throwing = "throwing")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwing) {
        System.out.println("方法：" + joinPoint.getSignature().getName() + " 异常通知:" + throwing);
    }

    @After(value = "myPointCut()")
    public void showFinallyEndLog() {
        System.out.println("最终通知");
    }
}
