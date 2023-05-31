package com.lilystu.utils;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

public class WebUtils {
    public static String getYearMonthDay() {
        //返回当前日期
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        String yearMonthDay = year + "/" + monthValue + "/" + dayOfMonth + "/";
        return yearMonthDay;
    }

    public static void SaveFile(FileItem fileItem, String fileReadPath) throws Exception {
        //                        获取文件名
        String name = fileItem.getName();
        System.out.println("文件名称:" + name);
        //为了防止保存文件失败（出现FileNotFoundException文件拒绝访问异常），要先创建好文件夹
        //写一个工具类，返回这样一个目录：2024/11/11 字符串
        File fileReadDir = new File(fileReadPath + WebUtils.getYearMonthDay());//获取文件
        if (!fileReadDir.exists()) {//文件夹不存在。。。
            fileReadDir.mkdirs();//创建文件夹
        }
//                        对上传的文件名进行处理,保证保存的上传文件名唯一
//                        System.currentTimeMillis()表示当前系统毫秒时间
        name = UUID.randomUUID().toString() + "_" +
                System.currentTimeMillis() + "_" + name;
        String fileFullPath = fileReadDir + "/" + name;//文件的全路径（加了文件名）
        fileItem.write(new File(fileFullPath));//将文件保存到创建好的文件中
    }
}
