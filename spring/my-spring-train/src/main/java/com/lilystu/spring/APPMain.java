package com.lilystu.spring;

import com.lilystu.spring.component.BookService;
import com.lilystu.spring.component.SmartAnimalable;
import com.lilystu.spring.ioc.SpringApplicationContext;
import com.lilystu.spring.ioc.SpringConfig;

public class APPMain {
    public static void main(String[] args) throws Exception{
        SpringApplicationContext ioc = new SpringApplicationContext(SpringConfig.class);
//        System.out.println(ioc.getBean("bookService"));
//        System.out.println(ioc.getBean("bookService"));
//        System.out.println(ioc.getBean("mBookDao"));
//        System.out.println(ioc.getBean("mBookDao"));

//        BookService bookService = (BookService)ioc.getBean("bookService");
//        bookService.ok();

        SmartAnimalable smartDog = (SmartAnimalable)ioc.getBean("smartDog");
        smartDog.getSum(10,20);
        System.out.println(smartDog.getSub(100,200));
    }
}
