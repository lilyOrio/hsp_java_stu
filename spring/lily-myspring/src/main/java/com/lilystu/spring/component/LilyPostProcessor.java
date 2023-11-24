package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.processe.BeanPostProcessor;

@Component
public class LilyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("LilyPostProcessor Before 方法执行。。。 bean=" + bean + ",beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("LilyPostProcessor After 方法执行。。。 bean=" + bean + ",beanName=" + beanName);
        return bean;
    }
}
