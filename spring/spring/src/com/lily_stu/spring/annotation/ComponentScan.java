package com.lily_stu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(value = {ElementType.TYPE}) 修饰自定义注解ComponentScan 可以修饰哪些程序元素
 * @Retention(RetentionPolicy.RUNTIME) 指定ComponentScan的作用范围（保留范围）
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//RetentionPolicy.RUNTIME:编译器将把注解记录在class 文件中. 当运行Java 程序时, JVM 会保留注解. 程序可以通过反射获取该注解
public @interface ComponentScan {

    String value() default "";//表示本注解可以传入一个value属性使用
}
