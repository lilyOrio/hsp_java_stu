package com.lilystu.text.furn.test.service;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.service.FurnService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class FurnServiceTest {
    private ApplicationContext ac;
    private FurnService furnService;

    @Before
    public void init() throws Exception {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        furnService = ac.getBean(FurnService.class);
//        furnService是一个代理对象
        System.out.println("furnService="+furnService.getClass());
    }

    @Test
    public void save() {
        Furn furn =
                new Furn(null, "北欧风格沙发~", "顺平家居~", new BigDecimal(180), 666,
                        7, null);
        furnService.save(furn);
        System.out.println("save ok");
    }

    @Test
    public void findAll() {
        List<Furn> furnList = furnService.findAll();
        for (Furn furn : furnList) {
            System.out.println(furn);
        }
    }

    @Test
    public void update() {
        Furn furn =
                new Furn(1, "优雅风格沙发", "顺平家居", new BigDecimal(10), 666,
                        7,null);
        furnService.update(furn);
        System.out.println("update ok");
    }

    @Test
    public void del() {
        furnService.del(1);
        System.out.println("del ok");
    }
}
