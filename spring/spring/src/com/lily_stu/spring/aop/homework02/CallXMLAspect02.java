package com.lily_stu.spring.aop.homework02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class CallXMLAspect02 {
    //切入点表达式重用
    @Pointcut(value = "execution(public int com.lily_stu.spring.aop.homework02.MyCal.*(int))")
    public void myPointCut() {
    }

    @Before(value = "myPointCut()")
    public void showBegin(JoinPoint joinPoint){
        System.out.println("CallXMLAspect02 前置通知~ 方法名：" + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "myPointCut()" ,returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        System.out.println("返回通知");
        Signature signature = joinPoint.getSignature();
// 3. 在目标方法结束后打印“方法结束”日志
        System.out.println("CallXMLAspect02 ~~~日志--方法名：" + signature.getName() + "--方法正常结束 --~" + "返回值：" + res);
    }

    @AfterThrowing(value = "myPointCut()",throwing = "throwing")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwing) {
        System.out.println("CallXMLAspect02 方法：" + joinPoint.getSignature().getName() + " 异常通知:" + throwing);
    }

    @After(value = "myPointCut()")
    public void showFinallyEndLog() {
        System.out.println("CallXMLAspect02 最终通知");
    }
}
