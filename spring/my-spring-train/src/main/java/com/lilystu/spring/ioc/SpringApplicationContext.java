package com.lilystu.spring.ioc;

import com.lilystu.spring.annotation.AutoWire;
import com.lilystu.spring.annotation.Component;
import com.lilystu.spring.annotation.ComponentScan;
import com.lilystu.spring.annotation.Scope;
import com.lilystu.spring.proesse.BeanPostProcessor;
import com.lilystu.spring.proesse.InitializingBean;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class SpringApplicationContext {
    private Class mConfig;
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<String, Object>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
    private List<BeanPostProcessor> processorList = new ArrayList<BeanPostProcessor>();

    public SpringApplicationContext(Class mConfig) throws Exception {
        this.mConfig = mConfig;
        initBeanDefinitionMap(mConfig);
//        System.out.println(beanDefinitionMap);
        System.out.println(processorList);

        //初始化singletonObjectMap
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()) {
            String id = keys.nextElement();
            BeanDefinition beanDefinition = beanDefinitionMap.get(id);
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = creatBean(id, beanDefinition);
                singletonObjectMap.put(id, bean);
            }
        }
//        System.out.println(singletonObjectMap);
    }

    public Object getBean(String id) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition != null) {
            Object bean;
            if (beanDefinition.getScope().equals("singleton")) {
                bean = singletonObjectMap.get(id);
            } else {
                bean = creatBean(id, beanDefinition);
            }
            return bean;
        } else {
            throw new NullPointerException("非法id");
        }
    }

    private Object creatBean(String name, BeanDefinition beanDefinition) throws Exception {
        Class clazz = beanDefinition.getClazz();
        Object instance = clazz.newInstance();
        //依赖注入
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoWire.class)) {
                Object bean = getBean(field.getName());
                field.setAccessible(true);
                field.set(instance, bean);
            }
        }
        //执行后置处理器Before方法
        for (BeanPostProcessor postProcessor : processorList) {
            Object o = postProcessor.beforeInit(instance, name);
            if (o != null) {
                instance = o;
            }
        }
        //执行初始化方法
        if (instance instanceof InitializingBean) {
            ((InitializingBean) instance).init();
        }

        //执行后置处理器After方法
        for (BeanPostProcessor postProcessor : processorList) {
            Object o = postProcessor.afterInit(instance, name);
            if (o != null) {
                instance = o;
            }
        }
        System.out.println("----------------------------");
        return instance;
    }

    private void initBeanDefinitionMap(Class mConfig) throws Exception {
        ComponentScan componentScan = (ComponentScan) mConfig.getAnnotation(ComponentScan.class);
        String value = componentScan.value();
//        System.out.println(value);
        String scanPath = value.replace(".", "/");
//        System.out.println(scanPath);

        ClassLoader loader = SpringApplicationContext.class.getClassLoader();
        URL url = loader.getResource(scanPath);
//        System.out.println(url);

        File file = new File(url.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
//                System.out.println(f);
                String fileFullName = f.getAbsolutePath();
                if (fileFullName.endsWith(".class")) {
                    String beanName = fileFullName.substring(fileFullName.lastIndexOf("\\") + 1, fileFullName.indexOf(".class"));
//                    System.out.println(beanName);
                    String classFullName = value + "." + beanName;
//                    System.out.println(classFullName);
                    Class<?> aClass = loader.loadClass(classFullName);
                    if (aClass.isAnnotationPresent(Component.class)) {
//                        System.out.println(aClass + "--是一个bean");
                        //扫描到后置处理器
                        if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                            processorList.add((BeanPostProcessor) aClass.newInstance());
                            continue;
                        }

                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(aClass);
                        if (aClass.isAnnotationPresent(Scope.class)) {
                            Scope scope = aClass.getAnnotation(Scope.class);
                            beanDefinition.setScope(scope.value());
                        } else {
                            beanDefinition.setScope("singleton");
                        }

                        Component component = aClass.getAnnotation(Component.class);
                        String id = component.value();
                        if (!"".equals(id)) {
                            beanName = id;
                        } else {
                            beanName = "m" + beanName;
                        }
                        beanDefinitionMap.put(beanName, beanDefinition);
                    }
                }
            }
        }
    }
}
