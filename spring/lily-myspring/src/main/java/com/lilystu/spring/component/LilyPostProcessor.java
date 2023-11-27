package com.lilystu.spring.component;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.aop.SmartAnimalAspect;
import com.lilystu.spring.processe.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class LilyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("LilyPostProcessor Before 方法执行。。。 bean=" + bean + ",beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if("mSmartDog".equals(beanName)){
            Object proxyInstance = Proxy.newProxyInstance(LilyPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(), new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("method = " + method);
                            Object invoke = null;
                            if ("getSum".equals(method.getName())){
                                SmartAnimalAspect.showBeginLog();
                                invoke = method.invoke(bean, args);
                                SmartAnimalAspect.showSuccessEndLog();
                            }else {
                                invoke = method.invoke(bean, args);
                            }
                            return invoke;
                        }
                    });
            System.out.println("LilyPostProcessor After 方法执行。。。 bean=" + proxyInstance.getClass() + ",beanName=" + beanName);
            return proxyInstance;
        }
        System.out.println("LilyPostProcessor After 方法执行。。。 bean=" + bean.getClass() + ",beanName=" + beanName);
        return bean;
    }
}
