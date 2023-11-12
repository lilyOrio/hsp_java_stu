package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.*;
import com.lily_stu.spring.component.UserDAO;
import com.lily_stu.spring.service.MemberServiceImpl;
import jdk.nashorn.internal.ir.LexicalContext;
import jdk.nashorn.internal.ir.LexicalContextNode;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SpringBeanTest {
    @Test
    public void getMonster() {
        //1.创建/获取容器 ApplicationContext
        //该容器和一个配置文件关联
        //目的：获取该配置文件（bean.xml）里面配置的JavaBean对象（有一个问题：为什么可以读取beans.xml文件
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //2.获取对象--通过id
        //默认返回的是object，运行类型还是Monster
//        Object monster01 = ioc.getBean("monster01");
        Monster monster01 = (Monster) ioc.getBean("monster01");
        //3.输出
        System.out.println("monster01==>" + monster01);
        System.out.println("monster01.class==>" + monster01.getClass());
        System.out.println("monster01.id==>" + monster01.getId());
        monster01.setId(200);
        System.out.println("ok~");
        //4.k以在获取的时候直接指定bean类型
        Monster monster011 = ioc.getBean("monster01", Monster.class);
        System.out.println("monster011==>" + monster011);
        System.out.println("monster011.id==>" + monster011.getId());

        //monster01和monster011是同一个对象

        //5.查看查看容器注入了哪些bean 对象,输出bean 的id
        String[] names = ioc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("--" + name);
        }
    }

    //有一个问题：为什么可以读取beans.xml文件
    //获取类加载的路径
    @Test
    public void classPath() {
        File file = new File(this.getClass().getResource("/").getPath());
        System.out.println("file==>" + file);
        //file==>D:\ uidq5850\repo_code\spring\spring\out\production\spring
    }

    @Test
    public void homeWork01() {//bean如果未指定id，就会使用默认id==》全类名+#+序号(从0开始
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster bean = (Monster) ioc.getBean("com.lily_stu.spring.bean.Monster#1");
        System.out.println("monster bean==>" + bean);
    }

    @Test
    public void homeWork02() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Car bean = (Car) ioc.getBean("car01");
        System.out.println("car bean==>" + bean);
    }

    /**
     * 通过类型来获取容器的bean 对象
     */
    @Test
    public void getBeanByType() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Car bean = ioc.getBean(Car.class);
        System.out.println("car bean==>" + bean);
    }

    /**
     * 通过构造器来设置属性
     */
    @Test
    public void setBeanByConstructor() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster03 = ioc.getBean("monster03", Monster.class);//可以不用类型转换
//        Monster monster03 =  (Monster) ioc.getBean("monster03");
        System.out.println("monster03 bean==>" + monster03);
    }

    @Test
    public void getMonsterByP() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster06 = ioc.getBean("monster06", Monster.class);
        System.out.println("monster04=" + monster06);
    }

    /**
     * 引用/注入其它bean 对象
     */
    @Test
    public void setBeanByRef() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        MemberServiceImpl memberServiceImpl = ioc.getBean("memberServiceImpl02",
                MemberServiceImpl.class);
        memberServiceImpl.add();
    }

    /**
     * 测试引用/注入集合/数组类型
     */
    @Test
    public void setCollectionByPro() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Master master01 = ioc.getBean("master01", Master.class);
        //获取list 集合
        System.out.println("======list=======");
        List<Monster> monster_list = master01.getMonsterList();
        for (Monster monster : monster_list) {
            System.out.println(monster);
        }
        System.out.println("======map=======");
        Map<String, Monster> monsterMap = master01.getMonsterMap();
        Set<Map.Entry<String, Monster>> entries = monsterMap.entrySet();
        for (Map.Entry<String, Monster> entry : entries) {
            System.out.println(entry);
        }
        System.out.println("======set=======");
        Set<Monster> monsterSet = master01.getMonsterSet();
        for (Monster monster : monsterSet) {
            System.out.println(monster);
        }
        System.out.println("======array=======");
        String[] monsterName = master01.getMonsterName();
        for (int i = 0; i < monsterName.length; i++) {
            System.out.println(monsterName[i]);
        }
        System.out.println("======properties=======");
        Properties pros = master01.getPros();
        Set<Map.Entry<Object, Object>> entries1 = pros.entrySet();
        for (Map.Entry<Object, Object> objectObjectEntry : entries1) {
            System.out.println(objectObjectEntry);
        }
    }

    @Test
    public void setBeanByUtilList() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        BookStore bookStore = ioc.getBean("bookStore", BookStore.class);
        List<String> bookList = bookStore.getBookList();
        System.out.println("bookList=" + bookList);
    }

    @Test
    public void setBeanByRelation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Emp emp = ioc.getBean("emp", Emp.class);
        System.out.println("emp=" + emp);
    }

    @Test
    public void getBeanByExtend() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster11 = ioc.getBean("monster11", Monster.class);
        System.out.println("monster11=" + monster11);
    }

    //单例多例
    @Test
    public void getBeanScope() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Cat cat1 = ioc.getBean("cat", Cat.class);
        Cat cat2 = ioc.getBean("cat", Cat.class);
        Cat cat3 = ioc.getBean("cat", Cat.class);
        System.out.println(cat1);
        System.out.println(cat2);
        System.out.println(cat3);
//        com.lily_stu.spring.bean.Cat@49ec71f8
//        com.lily_stu.spring.bean.Cat@1d2adfbe
//        com.lily_stu.spring.bean.Cat@36902638

        /*
        细节：
        Scope=singleton时bean对象在一开始就会创建（懒加载属性lazy_init是false
                        此时将lazy_init = true,那么该对象即是单例也是懒加载的
        scope=prototype时之后在getBean时才会创建对象（无论lazy_init是true还是false
         */
    }

    //测试Bean的生命周期
    @Test
    public void testBeanLife() {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");

        House house = ioc.getBean("house", House.class);

        System.out.println("使用house=" + house);

        //关闭容器
        //1. 这里又要考察大家的java基础
        //2. ioc的编译类型 ApplicationContext , 运行类型 ClassPathXmlApplicationContext
        //3. 因为ClassPathXmlApplicationContext 实现了 ConfigurableApplicationContext
        //4. ClassPathXmlApplicationContext 是有close
        //5. 将ioc 转成ClassPathXmlApplicationContext,再调用close
        //ioc.close();
        //关闭ioc容器.
        ((ConfigurableApplicationContext) ioc).close();
    }

    @Test
    public void setBeanByFile(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans.xml");
        Monster monster1000 = ioc.getBean("monster1000", Monster.class);
        System.out.println(monster1000);
    }

    //通过注解来配置bean
    @Test
    public void setBeanByAnnotation(){
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans05.xml");
        UserDAO userDAO = ioc.getBean(UserDAO.class);
//        在默认情况下类名首字母小写会作为对象的id 也可以在注释后面自定义id
        UserDAO userDAO01 = ioc.getBean("userDAO",UserDAO.class);

        System.out.println(userDAO);
    }
}
