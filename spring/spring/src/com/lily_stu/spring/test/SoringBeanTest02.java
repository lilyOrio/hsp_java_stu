package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.House;
import com.lily_stu.spring.proxy2.Car;
import com.lily_stu.spring.proxy2.Ship;
import com.lily_stu.spring.proxy2.Vehicle;
import com.lily_stu.spring.proxy2.VehicleProxyProvider;
import com.lily_stu.spring.proxy2.homework.SmartAnimal;
import com.lily_stu.spring.proxy2.homework.SmartAnimalProxyProvider;
import com.lily_stu.spring.service.OrderService;
import com.lily_stu.spring.web.OrderAction;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lily
 * @version 1.0
 */
public class SoringBeanTest02 {

    @Test
    public void testBeanPostProcessor(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans02.xml");

        House house = ioc.getBean("house2", House.class);
        House house3 = ioc.getBean("house3", House.class);

        System.out.println("使用house=" + house);
        System.out.println("使用house3=" + house3);
        ((ConfigurableApplicationContext)ioc).close();
    }

    @Test
    public void setBeanByAutowire(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("bean03.xml");
        OrderAction orderAction = ioc.getBean("orderAction", OrderAction.class);

        //验证是否自动装配上OrderService
        System.out.println(orderAction.getOrderService());
        //验证是否自动装配上OrderDao
        System.out.println(orderAction.getOrderService().getOrderDao());

        OrderService orderService = ioc.getBean("orderService", OrderService.class);
        System.out.println(orderService);
    }

    //动态代理
    @Test
    public void proxyRun(){
        Vehicle vehicle = new Car();
        VehicleProxyProvider vehicleProxyProvider =
                new VehicleProxyProvider(vehicle);
        //代理对象，可以代理vehicle执行方法,proxy的编译类型是Vehicle，运行类型是代理对象类型($Proxy9)，debug调试
        Vehicle proxy = vehicleProxyProvider.getProxy();
        System.out.println("proxy的编译类型是 Vehicle");
        System.out.println("proxy的运行类型是 " + proxy.getClass());
        //1、被代理的对象可以动态设置
        //2、
        String s = proxy.fly(100);//会执行到代理对象的invoked
        System.out.println(s);
    }

    //动态代理
    @Test
    public void proxyRunHomework(){
        SmartAnimalProxyProvider proxyProvider = new SmartAnimalProxyProvider(new SmartAnimal() {
            @Override
            public int getSum(int a, int b) {
                System.out.println("result = " + (a+b));
                return a + b;
            }

            @Override
            public int getSub(int a, int b) {
                System.out.println("result = " + (a-b));
                return a - b;
            }
        });

        SmartAnimal proxy = proxyProvider.getProxy();
        int sub = proxy.getSub(10, 20);
        int sum = proxy.getSum(100, 200);

    }
}
