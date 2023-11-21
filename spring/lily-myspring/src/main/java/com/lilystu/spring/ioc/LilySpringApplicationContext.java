package com.lilystu.spring.ioc;

import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.annotation.ComponentScan;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这个类的作用类似于Spring原生的ioc容器
 */
public class LilySpringApplicationContext {
    //只要是底层反射就一定要获取类的class对象
    private Class configClass;
    /**
     * 用于存放通过反射创建的对象
     */
    private final ConcurrentHashMap<String, Object> singletonObject = new ConcurrentHashMap<>();

    public LilySpringApplicationContext(Class configClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.configClass = configClass;//拿到配置类的Class对象就可以获取到config里面的注解以及注解的value
        System.out.println("this.configClass = " + this.configClass);
        //获取要扫描的包
        // 1.先得到注解
        ComponentScan componentScan = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        // 2.通过注解得到value 即要扫描的包
        String path = componentScan.value();
        System.out.println("path = " + path);
        path = path.replace(".", "/");
        // 3.扫描包下面的所有.class文件 注意真正需要加载的class文件在out目录
        //  a.获取到类的加载器
        ClassLoader loader = LilySpringApplicationContext.class.getClassLoader();
        //  b.通过类加载器获取包的资源URL 即路径
        URL resource = loader.getResource(path);
        System.out.println("resource = " + resource);
        //  c.将需要加载的资源（.class文件）进行遍历=>io
        File file = new File(resource.getFile());//目录也可以当作一个文件
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println("=============");
//                System.out.println(f.getAbsolutePath());
                String fileAbsolutePath = f.getAbsolutePath();//类的全路径
                // 过滤.class文件
                if (fileAbsolutePath.endsWith(".class")) {
                    // a.获取类名
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
//                System.out.println("className = " + className);
                    // b.获取类的全类名
                    String classFullName = path.replace("/", ".") + "." + className;
//                    System.out.println("classFullPath = " + classFullName);
                    // c.判断该类是否需要注入容器中（是否有对应注解
                    //   获得bean的Class对象
                    Class<?> aClass = loader.loadClass(classFullName);//通过全类名获取Class对象，类似于Class.forName(classFullName),后者会调用该类的静态方法，前者不会比较轻量级
                    if (aClass.isAnnotationPresent(Component.class)) {
                        //这是就可以反射对象，并放入到容器中
                        System.out.println(className + "是一个bean,全类名：" + classFullName);
                    }else {
                        System.out.println(className + "不是一个bean,全类名：" + classFullName);
                    }
                }
            }
        }
    }

//    public Object getBean(String id) {
//        return singletonObject.get(id);
//    }

}
