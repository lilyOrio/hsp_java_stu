package com.lily_stu.spring.lilyapplicationcontext;

import com.lily_stu.spring.bean.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现简单的Spring容器机制
 * 1.实现如何对beans.xml解析，并将创建的对象放入容器中
 * 2.提供一个获取对象的方法
 */
public class LilyApplicationContext {
    private ConcurrentHashMap<String, Object> singletonObject =
            new ConcurrentHashMap<>();

    //构造器
    //接收一个容器的配置文件，即beans.xml文件，该文件默认放在src下
    public LilyApplicationContext(String iocBeanXmlFile) throws Exception {
//        1.获取类加载路径
        String path = this.getClass().getResource("/").getPath();
        System.out.println(path);
//        2.创建SaxRead
        SAXReader saxReader = new SAXReader();
//        3.得到Document对象
        Document document = saxReader.read(new File(path + iocBeanXmlFile));
//        4.得到rootElement
        Element rootElement = document.getRootElement();
//        5.获取Bean节点--得到第一个Bean节点
        Element bean = (Element)rootElement.elements("bean").get(0);
//        6.获取对象id
        String id = bean.attributeValue("id");
//        7.获取类全路径
        String aClassPath = bean.attributeValue("class");
        System.out.println(id+"--"+aClassPath);
//        8.获取对象属性
        List<Element> property = bean.elements("property");
//        for (Element element : property) {
//            String name = element.attributeValue("name");
//        }
        Integer id_ = Integer.parseInt(property.get(0).attributeValue("value"));
        String name_ = property.get(1).attributeValue("value");
        String skill_ = property.get(2).attributeValue("value");
        System.out.println("id-"+id_);
        System.out.println("name-"+name_);
        System.out.println("skill-"+skill_);
//        9.使用反射创建对象
        Class<?> aClass = Class.forName(aClassPath);//拿到Class对象
        Monster o = (Monster)aClass.newInstance();//创建一个Monster对象
        System.out.println("o"+o);//对象已经创建，只不过还未赋值
        //使用反射赋值--后面完善
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        for (Method declaredMethod : declaredMethods) {
//            declaredMethod.invoke(aClass);
//        }
        //直接赋值
        o.setId(id_);
        o.setName(name_);
        o.setSkill(skill_);
        System.out.println("o"+o);

        singletonObject.put(id,o);
    }

    public Object getBean(String id){
        return singletonObject.get(id);
    }

    public LilyApplicationContext() {

    }
}
