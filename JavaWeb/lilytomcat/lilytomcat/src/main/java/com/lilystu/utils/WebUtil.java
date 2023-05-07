package com.lilystu.utils;

import com.lilystu.tomcat.LilyTomcatV3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WebUtil {
    //    将一个字符串转成数组
    public static int paresInt(String s, int defaultVal) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("数据格式有误！");
        }
        return defaultVal;
    }

    //    判断uri是否为html文件
    public static boolean isHtml(String uri) {
        return uri.endsWith(".html");
    }

    //    根据文件名来读取静态文件
    public static String readHtml(String filename) throws IOException {
        String path = WebUtil.class.getResource("/").getPath();
        path = path + filename;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
