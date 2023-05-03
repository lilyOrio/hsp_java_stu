package com.lilystu.servlet;

/**
 * @author lily
 * @version 1.0
 */
public class WebUtils {
    public static int parseString(String s){
        int num = 0;
        try {
            num = Integer.parseInt(s);
        }catch (Exception e){
            System.out.println("数据格式不对！");
        }
        return num;
    }
}
