package com.lilystu.spring.ioc;

import com.lilystu.spring.annotation.AutoWire;
import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.annotation.ComponentScan;
import com.lilystu.spring.annotation.Scope;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
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
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public LilySpringApplicationContext(Class configClass) throws Exception {
        beanDefinitionsByscan(configClass);
        System.out.println(beanDefinitionMap);

        //通过beanDefinitionMap , 初始化singletonObjects bean 单列池
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()){
            //得到beanName
            String beanName = keys.nextElement();
            //通过beanName 得到beanDefinition
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if ("singleton".equalsIgnoreCase(beanDefinition.getScope())){
                //将该bean 实例放入singletonObjects
                Object bean = createBean(beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
        System.out.println("singletonObjects 单例池= " + singletonObjects);
    }

    //先简单实现实现，后面在完善.
    private Object createBean(BeanDefinition beanDefinition) {
        //得到bean 的类型
        Class<?> clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(AutoWire.class)){
                    String name = field.getName();
                    Object bean = getBean(name);
                    field.setAccessible(true);
                    field.set(instance,bean);
                }
            }
            return instance;
        }catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有创建成功，返回null
        return null;
    }

    private void beanDefinitionsByscan(Class configClass) throws Exception {
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
                String fileAbsolutePath = f.getAbsolutePath();//类的全路径
                // 过滤.class文件
                if (fileAbsolutePath.endsWith(".class")) {
                    // a.获取类名
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
                    // b.获取类的全类名
                    String classFullName = path.replace("/", ".") + "." + className;
                    // c.判断该类是否需要注入容器中（是否有对应注解
                    //   获得bean的Class对象
                    Class<?> aClass = loader.loadClass(classFullName);//通过全类名获取Class对象，类似于Class.forName(classFullName),后者会调用该类的静态方法，前者不会比较轻量级
                    if (aClass.isAnnotationPresent(Component.class)) {
                        //获取bean的id
                        Component annotation = aClass.getDeclaredAnnotation(Component.class);
                        String id = annotation.value();
                        if ("".equals(id)) {
                            id = "m" + className;
                        }
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(aClass);
                        if (aClass.isAnnotationPresent(Scope.class)) {
                            Scope scope = aClass.getDeclaredAnnotation(Scope.class);
                            beanDefinition.setScope(scope.value());
                        } else {
                            beanDefinition.setScope("singleton");
                        }
                        beanDefinitionMap.put(id, beanDefinition);
                    } else {

                    }
                }
            }
        }
    }

    public Object getBean(String id) {
        if (beanDefinitionMap.containsKey(id)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(id);
            //得到bean 的scope , 分别处理
            if("singleton".equalsIgnoreCase(beanDefinition.getScope())) {
                //单例，直接从bean 单例池获取
                return singletonObjects.get(id);
            } else {
                //不是单例，则没有返回新的实例
                return createBean(beanDefinition);
            }
        }else {
            throw new NullPointerException("没有该bean");
        }
    }
}
