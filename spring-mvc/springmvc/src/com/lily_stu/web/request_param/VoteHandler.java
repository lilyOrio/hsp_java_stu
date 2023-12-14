package com.lily_stu.web.request_param;

import com.lily_stu.web.request_param.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/vote")
@Controller
public class VoteHandler {
    /**
     * 1.@RequestParam : 表示说明一个接受到的参数
     * 2.value="name" : 接收的参数名是name
     * 3.required=false : 表示该参数可以有，也可以没有,如果required=true,表示必须传递该参数.
     * 默认是required=true
     */
    @RequestMapping(value = "/vote01")
    public String test01(@RequestParam(value = "name", required = false)
                                 String username) {
        System.out.println("得到的username= " + username);
        //返回到一个结果
        return "success";
    }

    /**
     * 获取http 请求头信息
     * @param ae
     * @param host
     * @return
     */
    @RequestMapping(value = "/vote02")
    public String test02(@RequestHeader("Accept-Encoding") String ae,
                         @RequestHeader("Host") String host) {
        System.out.println("Accept-Encoding =" + ae);
        System.out.println("Host =" + host);
        //返回到一个结果
        return "success";
    }

    /**
     * 老韩解读
     * 1. 这里的字段名称和对象的属性名保持一致,级联添加属性也是一样保持名字对应关系
     * 2. 如果只是添加主人信息，则去掉宠物号和宠物名输入框即可,pet 为null-->
     * <form action="vote/vote03" method="post">
     * 主人号:<input type="text" name="id"><br>
     * 主人名:<input type="text" name="name"><br>
     * 宠物号:<input type="text" name="pet.id"><br>
     * 宠物名:<input type="text" name="pet.name"><br>
     * <input type="submit" value="添加主人和宠物">
     * </form>
     *
     * 支持级联数据获取
     * 表单的控件名称name 需要和javabean 对象字段对应, 否则就是null
     */
    //获取到添加的主人信息
    @RequestMapping(value = "/vote03")
    public String test03(Master master) {
        System.out.println("主人信息= " + master);
        //返回结果
        return "success";
    }

    /**
     * 获取servlet api
     * 使用servlet api , 需要引入tomcat/lib 下的servlet-api.jar
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/vote04")
    public String test04(HttpServletRequest request, HttpServletResponse response, HttpSession hs) {
        System.out.println("name= " + request.getParameter("username"));
        System.out.println("pwd= " + request.getParameter("pwd"));
        //可以看到hs 和request.getSession() 是同一个对象
        System.out.println("httpSession=" + hs);
        System.out.println("httpSession2=" + request.getSession());
        //返回结果
        return "success";
    }

    /**
     * 1. 除了HttpServletRequest, HttpServletResponse 还可以其它对象也可以这样的形式获取
     * 2. HttpSession、java.security.Principal,InputStream,OutputStream,Reader,Writer
     * 3. 其中一些对象也可以通过HttpServletRequest / HttpServletResponse 对象获取，比如
     * Session 对象,既可以通过参数传入，也以通过request.getSession() 获取，效果一样，推
     * 荐使用参数形式传入，更加简单明了
     */

}
