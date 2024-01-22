package com.lily_stu.web.json;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.InputStream;

@Controller
public class JsonHandler {
    @RequestMapping(value = "/getJson")
//指定返回的数据格式json ,靠这个@ResponseBody
    @ResponseBody
    public Dog getJson(){
        //创建一只狗
        Dog dog = new Dog();
        dog.setName("大黄狗");
        dog.setAddress("北京八达岭~");
        return dog;
    }

    @PostMapping("/save2")
    @ResponseBody
    public User save2(@RequestBody User user){
        //将前台传过来的数据以json 的格式相应回浏览器
        System.out.println("user~= " + user);
        return user;
    }

    @RequestMapping("/downFile")
    public ResponseEntity<byte[]> downFile(HttpSession session) throws Exception{
    //先获取下载文件的InputStream
        InputStream is = session.getServletContext().getResourceAsStream("/img/小约翰1.jpeg");
        //开辟一个存放文件内容byte 数组,因为byte 是字节，因此可以返回二进制的文件【图片，视频。。】
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        //构建返回的ResponseEntity<byte[]>
        HttpStatus status = HttpStatus.OK;//返回成功
        //根据http 协议这个就是告诉浏览器，这是返回的一个文件,浏览器就弹出小窗口
        //文件下载响应头的设置
        //content-type 指示响应内容的格式
        //content-disposition 指示如何处理响应内容。
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=小约翰1.jpeg");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        return responseEntity;
    }
}
