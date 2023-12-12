package com.lily_stu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserHandler {

    /**
     * 1. method=RequestMethod.POST: 表示请求buy 目标方法必须是post
     * 2. RequestMethod 四个选项POST, GET, PUT, DELETE
     * 3. SpringMVC 控制器默认支持GET 和POST 两种方式
     * @return
     */
    @RequestMapping(value = "/buy",method = RequestMethod.GET)
    public String buy() {
        System.out.println("购买商品");
        return "success";
    }

    /**
     * params="bookId" 表示请求该目标方法时，必须给一个bookId 参数
     * @param bookId 就是参数bookId携带的值，名称要一直
     * @return
     * 1. ?：匹配文件名中的一个字符
     * 2. *：匹配文件名中的任意字符
     * 3. **:匹配多层路径
     */
    @RequestMapping(value = "/**/find", params = "bookId=100", method = RequestMethod.GET)
    public String search(String bookId) {
        System.out.println("查询书籍bookId= " + bookId);
        return "success";
    }

    /**
     *  @RequestMapping 可配合@PathVariable 映射URL 绑定的占位符
     * 1. @RequestMapping 还可以配合@PathVariable 映射URL 绑定的占位符。
     * 要求： 我们希望目标方法获取到 username 和 userid, value="/xx/{username}" - @PathVariable("username")..
     * 前端页面: <a href="user/reg/kristina/300">占位符的演示</a>
     * (value = "/reg/{username}/{userid}"): 表示kristina->{username} 300=>{userid}
     */
    @RequestMapping("/reg/{bookId}/{bookName}")
    public String register(@PathVariable("bookId") String name,
                           @PathVariable("bookName") String id){
        System.out.println("接收到参数--" + "username= " + name + "--" + "usreid= " + id);
        return "success";
    }

    /*
    注意事项和使用细节
    映射的URL, 不能重复
    
     */
}
