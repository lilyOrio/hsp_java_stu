package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.aop.SmartAnimalAspect;
import com.lilystu.spring.proesse.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public Object beforeInit(Object bean, String beanName) {
        System.out.println("beforeInit "+beanName+"="+bean);
        return bean;
    }

    public Object afterInit(final Object bean, String beanName) {
        if ("smartDog".equals(beanName)){
            SmartAnimalable proxyInstance = (SmartAnimalable)Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                           if ("getSub".equals(method.getName())){
                               SmartAnimalAspect.showBeginLog();
                               Object res = method.invoke(bean, args);
                               SmartAnimalAspect.showSuccessEndLog();
                               return res;
                           }else {
                               return method.invoke(bean, args);
                           }
                        }
                    });
            System.out.println("AfterInit "+beanName+"="+bean);
            return proxyInstance;
        }
        System.out.println("AfterInit "+beanName+"="+bean);
        return bean;
    }
}
