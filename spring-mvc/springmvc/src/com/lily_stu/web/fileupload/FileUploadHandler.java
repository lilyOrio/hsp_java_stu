package com.lily_stu.web.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class FileUploadHandler {
    @RequestMapping(value="/fileUpload")
    public String fileUpload(@RequestParam(value = "file")MultipartFile multipartFile,
                             HttpServletRequest request)throws Exception{
        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println("上传文件名称=" + originalFilename);
        String realPath = request.getServletContext().getRealPath("/img/" + originalFilename);
        //转存到 d:/kk
        File saveToFile = new File(realPath);
        multipartFile.transferTo(saveToFile);
        return "success";
    }
}
