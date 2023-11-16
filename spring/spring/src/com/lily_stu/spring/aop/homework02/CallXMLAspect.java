package com.lily_stu.spring.aop.homework02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class CallXMLAspect {
    public void showBegin(JoinPoint joinPoint) throws Throwable {
        System.out.println("CallXMLAspect 前置通知~ 方法名：" + joinPoint.getSignature().getName());
    }
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        System.out.println("返回通知");
        Signature signature = joinPoint.getSignature();
// 3. 在目标方法结束后打印“方法结束”日志
        System.out.println("CallXMLAspect~~~日志--方法名：" + signature.getName() + "--方法正常结束 --~" + "返回值：" + res);
    }

    public void showExceptionLog(JoinPoint joinPoint, Throwable throwing) {
        System.out.println("CallXMLAspect 方法：" + joinPoint.getSignature().getName() + " 异常通知:" + throwing);
    }

    public void showFinallyEndLog() {
        System.out.println("CallXMLAspect 最终通知");
    }
}
