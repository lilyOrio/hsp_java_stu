package com.lilystu.springboot.lambda;

import org.springframework.util.StringUtils;

/**
 * 模拟lambda语言
 */
public class Test {
    public static void main(String[] args) {

        //正常写法
        LilyFunction<Cat,String> f = new LilyFunction<Cat, String>() {
            @Override
            public String apply(Cat cat) {
                return "hi cat";
            }
        };
        String s = f.apply(new Cat());
        System.out.println(s);

        //lambda写法
        LilyFunction<Cat,String> f1 = Cat::getName;
        f1.apply(new Cat());
        f1.hi();
    }
}

@FunctionalInterface
interface LilyFunction<T, R> {

    //public void hi();
    // 这个方法表示的是 根据类型 T 的参数获取类型 R 的结果
    // R 类型的结果
    R apply(T t);

    //可以有多个默认实现方法
    default public void hi() {
        System.out.println("hi");
    }
}

class Cat{
    private String name;

    public String getName() {
        return name;
    }
}