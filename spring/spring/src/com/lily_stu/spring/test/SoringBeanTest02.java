package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.House;
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

}
