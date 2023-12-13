package com.lily_stu.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class BookHandler {

    //查询[GET]
    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") String id) {
        System.out.println("查询书籍id=" + id );
        return "success";
    }

    //添加[POST]
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public String addBook(String bookName) {
        System.out.println("添加书籍bookName== " + bookName);
        return "success";
    }

    @DeleteMapping("/book/{id}")
    //删除[DELETE]
    public String delBook(@PathVariable("id") String id) {
        System.out.println("删除书籍id= " + id);
//return "success"; [如果这样返回会报错JSPs only permit GET POST or HEAD]
        return "redirect:/user/success"; //重定向到一个没有指定method 的Handler 方法
    }

    //修改[PUT]
    @PutMapping("/book/{id}")
    public String updateBook(@PathVariable("id") String id) {
        System.out.println("修改书籍id=" + id);
        return "redirect:/user/success"; //重定向到一个没有指定method 的Handler 方法
    }

    @RequestMapping(value = "/success")
    public String successGenecal() {
        System.out.println("ok~");
        return "success"; //由该方法转发到success.jsp 页面
    }
}
