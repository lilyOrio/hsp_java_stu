package com.lily_stu.spring.lilyapplicationcontext;

import com.lily_stu.spring.bean.Monster;

public class LilyApplicationContextTest {
    public static void main(String[] args) throws Exception{
        LilyApplicationContext lilyApplicationContext = new LilyApplicationContext("beans.xml");
        System.out.println("ok~");
        Monster monster01 = (Monster)lilyApplicationContext.getBean("monster01");
        System.out.println("monster01~"+monster01);
        System.out.println("monster01_id~"+monster01.getId());
    }
}
