package com.lilystu.utils;

public class WebUtil {
//    将一个字符串转成数组
    public static int paresInt(String s,int defaultVal){
        try {
            return Integer.parseInt(s);
        }catch (Exception e){
            System.out.println("数据格式有误！");
        }
        return defaultVal;
    }
}
