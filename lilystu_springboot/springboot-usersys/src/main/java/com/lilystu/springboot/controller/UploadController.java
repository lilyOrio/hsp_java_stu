package com.lilystu.springboot.controller;

import com.lilystu.springboot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {

    //转发到用户注册界面
    @GetMapping("/upload.html")
    public String uploadPage() {
        return "upload";
    }

    //处理文件上传请求
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("age") String age,
                         @RequestParam("job") String job,
                         @RequestParam("header") MultipartFile header,
                         @RequestParam("photos") MultipartFile[] photos) throws IOException {
        log.info("name={}, email={}, age={}, job={}, header={}, photos={}", name, email, age, job, header.getSize(), photos.length);
        //1.先将文件保存到指定目录 文件夹必须存在 d:\\imgs\\

        //2.将文件保存到动态创建的目录 文件夹可能不存在
        // D:\idq5850\repo_code\lilystu_springboot\springboot-usersys\target\classes\static\image\xxx

        //3.解决同名文件覆盖问题
        //4.根据日期分目录存放
        String path = ResourceUtils.getURL("classpath:").getPath();//类路径
        log.info("path={}", path); //==>D:\idq5850\repo_code\lilystu_springboot\springboot-usersys\target\classes\
        File file = new File(path + WebUtils.getUploadImgDirectory());
        if (!file.exists()) {
            file.mkdirs();
        }
        log.info("file.path={}", file.getAbsolutePath());

        if (!header.isEmpty()) {//头像文件
            String originalFilename = header.getOriginalFilename();
//            header.transferTo(new File("d:\\imgs\\" + originalFilename));
            String fileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "-" + originalFilename;
            header.transferTo(new File(file.getAbsolutePath() + "/" + fileName));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();
//                    photo.transferTo(new File("d:\\imgs\\" + originalFilename));
                    String fileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "-" + originalFilename;
                    photo.transferTo(new File(file.getAbsolutePath() + "/" + fileName));
                }
            }
        }


        return "文件上传成功！";
    }

}
