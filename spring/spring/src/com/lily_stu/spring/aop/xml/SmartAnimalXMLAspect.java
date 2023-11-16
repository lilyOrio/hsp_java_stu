package com.lily_stu.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

public class SmartAnimalXMLAspect {

    public void showBegin(JoinPoint joinPoint) {
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        System.out.println("日志--方法名：" + signature.getName() + "--方法开始--参数： " +
                Arrays.asList(joinPoint.getArgs()));
    }

    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        System.out.println("返回通知");
        Signature signature = joinPoint.getSignature();
// 3. 在目标方法结束后打印“方法结束”日志
        System.out.println("~~~日志--方法名：" + signature.getName() + "--方法正常结束 --~" + "返回值：" + res);
    }

    public void showExceptionLog(JoinPoint joinPoint, Throwable throwing) {
        System.out.println("方法：" + joinPoint.getSignature().getName() + " 异常通知:" + throwing);
    }

    public void showFinallyEndLog() {
        System.out.println("最终通知");
    }

    public void ok(JoinPoint joinPoint) {
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        System.out.println("日志--方法名：" + signature.getName() + "--方法开始--参数： " +
                Arrays.asList(joinPoint.getArgs()));
    }

}
