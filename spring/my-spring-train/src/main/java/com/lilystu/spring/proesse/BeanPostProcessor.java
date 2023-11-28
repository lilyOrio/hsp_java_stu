package com.lilystu.spring.proesse;

public interface BeanPostProcessor {
    Object beforeInit(Object bean,String beanName);
    Object afterInit(Object bean,String beanName);
}
