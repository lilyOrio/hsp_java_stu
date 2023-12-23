package com.lilystu.lilyspringmvc.context;

import com.lilystu.lilyspringmvc.annotation.Controller;
import com.lilystu.lilyspringmvc.xml.XMLPaser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LilyWebApplicationContext {
    private final List<String> classFullPathList = new ArrayList<String>();
    //ioc 用于存放反射后的bean 对象.
    public ConcurrentHashMap<String, Object> ioc =
            new ConcurrentHashMap<>();

    public void init() {
        String aPackage = XMLPaser.getbasePackage("lilyspringmvc.xml");
        String[] packs = aPackage.split(",");
        if (packs.length > 0) {
            for (String pack : packs) {
                scanPackage(pack);
            }
        }
//        System.out.println(classFullPathList);
        executeInstance();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ioc);
    }
}
