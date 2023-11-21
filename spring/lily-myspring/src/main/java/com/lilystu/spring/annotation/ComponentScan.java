package com.lilystu.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//定义我们的ComponentScan 注解,用来标注需要扫描的包
public @interface ComponentScan {
    String value();
}
