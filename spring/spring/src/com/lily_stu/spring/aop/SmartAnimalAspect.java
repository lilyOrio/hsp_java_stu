package com.lily_stu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect //表示这个类是一个切面类
@Component //需要加入到IOC 容器
//这个就对应动态代理类的
public class SmartAnimalAspect {
    @Before(value = "execution(public * com.lily_stu.spring.aop.*.*.*(..))")
    public void showBegin(JoinPoint joinPoint) {
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        System.out.println("日志--方法名：" + signature.getName() + "--方法开始--参数： " +
                Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(public float com.lily_stu.spring.aop.aspectj.SmartDog.getSub(float,float))")
    public void showSuccessEndLog(JoinPoint joinPoint) {
        System.out.println("返回通知");
        Signature signature = joinPoint.getSignature();
// 3. 在目标方法结束后打印“方法结束”日志
        System.out.println("日志--方法名：" + signature.getName() + "--方法正常结束 --~");
    }

    @AfterThrowing(value = "execution(public float com.lily_stu.spring.aop.aspectj.SmartDog.getSub(float,float))")
    public void showExceptionLog() {
        System.out.println("异常通知");
    }

    @After(value = "execution(public * com.lily_stu.spring.aop.*.*.*(..))")
    public void showFinallyEndLog() {
        System.out.println("最终通知");
    }
}
