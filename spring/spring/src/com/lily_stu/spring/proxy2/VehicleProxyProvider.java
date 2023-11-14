package com.lily_stu.spring.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 该类返回一个代理对象
 */
public class VehicleProxyProvider {

    private Vehicle target_vehicle;//代理对象，需实现Vehicle接口

    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    //编写一个方法，返回代理对象
    //该方法十分重要
    //
    public Vehicle getProxy(){
        //得到类加载器
        ClassLoader loader = target_vehicle.getClass().getClassLoader();
        //得到代理对象的接口信息，底层是通过接口来调用的
        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();
        //InvocationHandler 创建调用处理器，通过匿名对象的方式创建
        /*
        public interface InvocationHandler {
            public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
        }
         */

        /*
            public static Object newProxyInstance(ClassLoader loader,//加载器
                                          Class<?>[] interfaces,//要代理的对象的接口信息
                                          InvocationHandler h)//调用处理器/对象，有个方法invoke用来执行target_vehicle的方法
         */
        //proxy就是代理对象
        Vehicle proxy = (Vehicle)Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
            @Override//proxy 代理对象；method 通过代理对象调用的方法proxy.run(xx)；args 调用代理方法时传入的参数
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("交通工具开始运行了...");
                //method : run()
                //target_vehicle : Ship
                Object result = method.invoke(target_vehicle,args);//反射调用 方法.invoke(对象，参数)；动态代理
                System.out.println("交通工具停止运行了...");
                return result;//调用代理方法返回的结果
            }
        });
        return proxy;
    }

    public Vehicle getProxy02(){
       return (Vehicle)Proxy.newProxyInstance(target_vehicle.getClass().getClassLoader(),
                target_vehicle.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("begin~");
                        Object o = method.invoke(target_vehicle, args);
                        System.out.println("end~");
                        return o;
                    }
                });
    }
}
