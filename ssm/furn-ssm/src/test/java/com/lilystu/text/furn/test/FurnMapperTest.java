package com.lilystu.text.furn.test;

import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.dao.FurnMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class FurnMapperTest {

    @Test
    public void insertSelective() {
        //1、创建 SpringIOC 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2、从容器中获取 mapper
        FurnMapper furnMapper = ac.getBean(FurnMapper.class);
        //如果这里 OK, 说明 spring 和 mybatis 整合 OK
        System.out.println(furnMapper);
        Furn furn = new Furn(null, "北欧风格沙发~", "顺平家居~", new BigDecimal(180), 666, 7, "assets/images/product-image/1.jpg");
        furnMapper.insertSelective(furn);
        System.out.println("添加 OK");
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
        //1、创建 SpringIOC 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2、从容器中获取 mapper
        FurnMapper furnMapper = ac.getBean(FurnMapper.class);
        int affectedRow = furnMapper.deleteByPrimaryKey(3);
        System.out.println(affectedRow > 0 ? "删除成功" : "没有删除数据");
    }

    @Test
    public void updateByPrimaryKey() {
        //1、创建 SpringIOC 容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 2、从容器中获取 mapper
        FurnMapper furnMapper = ac.getBean(FurnMapper.class);
        Furn furn = new Furn(4, "北欧盆景", "顺平家居", new BigDecimal(90), 666, 7, "assets/images/product-image/4.jpg");
        int affectedRow = furnMapper.updateByPrimaryKey(furn);
        System.out.println(affectedRow > 0 ? "操作成功" : "没有影响表数据");
    }


}
