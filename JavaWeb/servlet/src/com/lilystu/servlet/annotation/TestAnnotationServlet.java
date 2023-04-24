package com.lilystu.servlet.annotation;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;

/**
 * 模拟tomcat如何通过注解 @WebServlet(urlPatterns = {"/ok1","/ok2"})
 * 来装载一个OkServlet
 * 反射+注解+io+集合0
 */
public class TestAnnotationServlet {
    public static final HashMap<String, Object> map = new HashMap<>();
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        获取扫描的包的路径
        String classAllPath = "com.lilystu.servlet.annotation.OkServlet";
//        得到OkServlet的Class 对象
        Class<?> aClass = Class.forName(classAllPath);
//        通过Class对象获取Annotation
        WebServlet annotation = aClass.getAnnotation(WebServlet.class);
        System.out.println(annotation);
        String[] urls = annotation.urlPatterns();
        for (String url : urls) {
            System.out.println("url=" + url);
        }
//        第一次匹配url，如果不存在OkServlet实例，Tomcat会创建一个OkServlet实例并存放到hashMap中
        Object instance = aClass.newInstance();
        map.put("OkServlet",instance);
        System.out.println("map=" + map);
    }
}
