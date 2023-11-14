package com.lily_stu.spring.proxy3;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 对土方法解耦-开发简易的AOP 类
 */
public class LilyAOP {
    public static void before(Object proxy, Method method, Object[] args) {
        System.out.println(method.getName() + "-" + Arrays.asList(args));
    }

    public static void after(Method method, Object res) {
        System.out.println("方法执行结束==》" + method.getName() + "-" + "res:" + res);
    }

}
