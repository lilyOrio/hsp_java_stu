package com.lily_stu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(value = 2)//切片类优先级 数字越小优先级越高，优先级越低前置通知执行越后，但是返回通知、异常通知和最终通知会先执行，类似于过滤器
@Aspect //表示这个类是一个切面类
@Component //需要加入到IOC 容器
//这个就对应动态代理类的
public class SmartAnimalAspect {

    //切入点表达式重用
    @Pointcut(value = "execution(public float com.lily_stu.spring.aop.aspectj.SmartDog.getSub(float,float))")
    public void myPointCut() {
    }

    @Before(value = "execution(public * com.lily_stu.spring.aop.*.*.*(..))")
    public void showBegin(JoinPoint joinPoint) {
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        System.out.println("日志--方法名：" + signature.getName() + "--方法开始--参数： " +
                Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "myPointCut()", returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint, Object res) {
        System.out.println("返回通知");
        Signature signature = joinPoint.getSignature();
// 3. 在目标方法结束后打印“方法结束”日志
        System.out.println("~~~日志--方法名：" + signature.getName() + "--方法正常结束 --~" + "返回值：" + res);
    }

    @AfterThrowing(value = "execution(public float com.lily_stu.spring.aop.aspectj.SmartDog.getSub(float,float))", throwing = "throwing")
    public void showExceptionLog(JoinPoint joinPoint, Throwable throwing) {
        System.out.println("方法：" + joinPoint.getSignature().getName() + " 异常通知:" + throwing);
    }

    @After(value = "execution(public void com.lily_stu.spring.aop.homework.UsbInterface.work(String))")
    public void showFinallyEndLog() {
        System.out.println("最终通知");
    }

    @Before(value = "execution(public void Car.run())")
    public void ok(JoinPoint joinPoint) {
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        System.out.println("日志--方法名：" + signature.getName() + "--方法开始--参数： " +
                Arrays.asList(joinPoint.getArgs()));
    }

    /*JoinPoint
    通过JoinPoint 可以获取到调用方法的签名，和其它相关信息
        joinPoint.getSignature().getName(); // 获取目标方法名
        joinPoint.getSignature().getDeclaringType().getSimpleName(); // 获取目标方法所属类的简单类名
        joinPoint.getSignature().getDeclaringTypeName(); // 获取目标方法所属类的类名
        joinPoint.getSignature().getModifiers(); // 获取目标方法声明类型(public、private、protected)
        Object[] args = joinPoint.getArgs(); // 获取传入目标方法的参数，返回一个数组
        joinPoint.getTarget(); // 获取被代理的对象
        joinPoint.getThis(); // 获取代理对象自己
     */
}
