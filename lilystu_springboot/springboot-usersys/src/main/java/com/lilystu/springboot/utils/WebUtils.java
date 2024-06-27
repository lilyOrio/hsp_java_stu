package com.lilystu.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebUtils {

    //定义一个上传文件的路径
    public static String UPLOAD_IMG_DIRECTORY = "static/image/upload/";

    //生成一个目录，依据当前日期 y/m/d

    public static String getUploadImgDirectory(){
        return UPLOAD_IMG_DIRECTORY + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }
}
