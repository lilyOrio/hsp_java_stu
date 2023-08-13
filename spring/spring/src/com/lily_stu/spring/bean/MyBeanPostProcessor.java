package com.lily_stu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author lily
 * @version 1.0
 * 这是一个后置处理器
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在bean对象的init方法调用之前调用
     *
     * @param bean     传入在ioc容器中创建的bean对象
     * @param beanName bean的id
     * @return bean对象，可能被处理过
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization... bean=" + bean +
                "beanName=" + beanName);
        if (bean instanceof House){
            ((House)bean).setName("桃江御景");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization... bean=" + bean +
                "beanName=" + beanName);
        return bean;
    }
}
