package com.lily_stu.spring.proxy3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SmartAnimalProxyProvider {
    private SmartAnimal smartAnimal;

    public SmartAnimalProxyProvider(SmartAnimal smartAnimal) {
        this.smartAnimal = smartAnimal;
    }

//    public void before(Object proxy, Method method, Object[] args) {
//        System.out.println(method.getName() + "-" + Arrays.asList(args));
//    }
//
//    public void after(Method method, Object res) {
//        System.out.println("方法执行结束==》" + method.getName() + "-" + "res:" + res);
//    }

    public SmartAnimal getProxy() {
        ClassLoader loader = smartAnimal.getClass().getClassLoader();
        Class<?>[] interfaces = smartAnimal.getClass().getInterfaces();
        SmartAnimal proxy = (SmartAnimal) Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object res = null;
                try {
                    LilyAOP.before(proxy, method, args);
                    res = method.invoke(smartAnimal, args);
                    LilyAOP.after(method, res);
                    return res;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("方法执行异常。。。");
                } finally {
                    System.out.println("finally...");
                }
                return res;
            }
        });
        return proxy;
    }
}
