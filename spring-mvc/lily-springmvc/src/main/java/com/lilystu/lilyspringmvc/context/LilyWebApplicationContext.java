package com.lilystu.lilyspringmvc.context;

import com.lilystu.lilyspringmvc.annotation.AutoWired;
import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.annotation.Service;
import com.lilystu.lilyspringmvc.xml.XMLPaser;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LilyWebApplicationContext {
    private final List<String> classFullPathList = new ArrayList<String>();
    //ioc 用于存放反射后的bean 对象.
    public ConcurrentHashMap<String, Object> ioc =
            new ConcurrentHashMap<>();

    public LilyWebApplicationContext() {
    }

    //这个就是我们的spring 容器配置文件,
    //这个contextConfigLocation 形式为classpath:xx.xml
    private String configLocation = "";

    public LilyWebApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }

    public void init() {
        String aPackage = XMLPaser.getbasePackage(configLocation.split(":")[1]);
        String[] packs = aPackage.split(",");
        if (packs.length > 0) {
            for (String pack : packs) {
                scanPackage(pack);
            }
        }
//        System.out.println(classFullPathList);
        executeInstance();

        //完成Spring 容器中对象的自动装配/注入
        executeAutoWired();
    }

    public void scanPackage(String pack) {
        URL url = this.getClass().getClassLoader().
                getResource("/" + pack.replaceAll("\\.", "/"));
//        url = file:/D:/uidq5850/repo_code/spring-mvc/lily-springmvc/target/lily-springmvc-1.0-SNAPSHOT/WEB-INF/classes/com/lilystu/controller/
//        System.out.println("url = " + url);

        String path = url.getFile();
        File dir = new File(path);
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                //如果是目录, 递归扫描
                scanPackage(pack + "." + f.getName());
            } else {
                //如果是文件, 也是就.class 类
                //获取到类的全路径，并放入到classFullPathList 集合
                String classFullPath = pack + "." + f.getName().replace(".class", "");
                classFullPathList.add(classFullPath);
            }
        }
    }

    /**
     * - 将扫描到的类, 在满足条件的情况下(即有相应的注解@Controller @Service...), 反射注入到ioc 容器
     */
    public void executeInstance() {
        if (classFullPathList.size() == 0) {
            //抛出一个异常，或者抛出一个自定义异常
            //throw new RuntimeException("没有需要实例化的对象");
            //我这里直接return;
            return;
        }
        try {
            for (String classFullPath : classFullPathList) {
                Class<?> clazz = Class.forName(classFullPath);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    //得到该类的类名(首字母小写), 作为key
                    String beanName = clazz.getSimpleName().substring(0, 1).toLowerCase() +
                            clazz.getSimpleName().substring(1);
                    ioc.put(beanName, clazz.newInstance());
                }//如果有其它注解，可以在这里扩展

                if (clazz.isAnnotationPresent(Service.class)) {
                    Service serviceAnnotation = clazz.getAnnotation(Service.class);
                    String beanName = serviceAnnotation.value();
                    if ("".equals(beanName)) {
                        //如果@Service 没有指定value
                        //得到该Service 的所有接口名(首字母小写)
                        //相当于可以通过该类的多个接口名来,注入该Service 实例
                        Object instance = clazz.newInstance();
                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> anInterface : interfaces) {
                            String beanName2 = anInterface.getSimpleName().substring(0, 1).toLowerCase() +
                                    anInterface.getSimpleName().substring(1);
                            ioc.put(beanName2, instance);
                        }
                    } else {
                        ioc.put(beanName, clazz.newInstance());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(ioc);
    }

    private void executeAutoWired() {
        if (ioc.isEmpty()) {
            throw new RuntimeException("容器中, 没有可以装配的bean");
        }
        //遍历ioc 容器中所有，已经实例化的bean
        Set<Map.Entry<String, Object>> entrySet = ioc.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            Object o = entry.getValue();
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AutoWired.class)) {
                    AutoWired autoWired = field.getAnnotation(AutoWired.class);
                    String filedName = autoWired.value();
                    if ("".equals(filedName)) {
                        //未指定要注入的bean的名字
                        Class<?> type = field.getType();
                        //获取被标注@AutoWired 字段的类型的名字(首字母小写)
                        filedName = type.getSimpleName().substring(0, 1).toLowerCase() +
                                type.getSimpleName().substring(1);
                    }
                    //放在该@AutoWired 属性是private
                    field.setAccessible(true);
                    try {
                        if (ioc.get(filedName) == null) {
                            throw new RuntimeException("ioc 容器中, 没有可以注入的bean");
                        }
                        field.set(o, ioc.get(filedName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
