package com.lily_stu.web.request_param;

import com.lily_stu.web.request_param.entity.Master;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
     * <p>
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
     * 1. 除了HttpServletRequest, HttpServletResponse 还可以其它对象也可以这样的形式获取
     * 2. HttpSession、java.security.Principal,InputStream,OutputStream,Reader,Writer
     * 3. 其中一些对象也可以通过HttpServletRequest / HttpServletResponse 对象获取，比如
     * Session 对象,既可以通过参数传入，也以通过request.getSession() 获取，效果一样，推
     * 荐使用参数形式传入，更加简单明了
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
     * 获取模型数据master, 并放入到request
     */
    @RequestMapping(value = "/vote05")
    public String test05(Master master,
                         HttpServletRequest request, HttpServletResponse response) {
        //老韩解读
        //1. springmvc 会自动把获取的model 模型，放入到request 域中，名字就是master
        //2. 也可以手动将master 放入到request
        //request.setAttribute("master", master);
        request.setAttribute("address", "北京");
        //返回到一个结果
        return "vote_ok";
    }

    /**
     * 获取到添加的主人信息，并加入到Map<String,Object> 中
     */
    @RequestMapping(value = "/vote06")
    public String test06(Map<String, Object> map, Master master) {
        System.out.println("=====test06()=====~");
        //老韩解读
        //1. springMVC 会将map 的k-v 放入到request 域对象
        map.put("master", master);
        map.put("address", "beijing");
        //返回到一个结果
        return "vote_ok";
    }

    /**
     * 1. 将model 放入到ModelAndView 对象中，实现将数据放入到request 域中
     * 1) 从本质看，请求响应的方法return "xx", 是返回了一个字符串，其实本质是返回了一个ModelAndView 对象，只是默认被封装起来的.
     * 2) ModelAndView 即可以包含model 数据，也可以包含视图信息
     * 3) ModelAndView 对象的addObject 方法可以添加key-val 数据，默认在request 域中
     * 4) ModelAndView 对象setView 方法可以指定视图名称
     */
    @RequestMapping(value = "/vote07")
    public ModelAndView test07(Master master) {
        System.out.println("======test07()======");
        //创建一个ModelAndView 对象
        ModelAndView modelAndView = new ModelAndView();
        //下面这句话就等价于将master 对象放入到request 域中，属性名“master”
        modelAndView.addObject("master", master);
        modelAndView.addObject("address", "shanghai");
        modelAndView.setViewName("vote_ok");
        //返回结果
        return modelAndView;
    }

    /**
     * 将model(master 对象) 放入到session 域中
     */
    @RequestMapping(value = "/vote08")
    public String test08(Map<String, Object> map, Master master,
                         HttpSession session) {
        System.out.println("======test08()======");
        map.put("address", "guangzhou");
        //在session 域也放入master 对象
        session.setAttribute("master2", master);
        return "vote_ok";
    }

    /**
     * 1. 当在某个方法上，增加了@ModelAttribute 注解
     * 2. 那么在调用该Handler 的任何一个方法时，都会先调用这个方法
     *
     * 6.3.2 @ModelAttribute 最佳实践
     * ● 修改用户信息（就是经典的使用这种机制的应用），流程如下:
     * 1. 在修改前，在前置方法中从数据库查出这个用户
     * 2. 在修改方法(目标方法)中，可以使用前置方法从数据库查询的用户
     * 3. 如果表单中对用户的某个属性修改了，则以新的数据为准，如果没有修改，则以数据库
     * 的信息为准，比如，用户的某个属性不能修改，就保持原来的值
     */
    @ModelAttribute
    public void prepareModel() {
        System.out.println("prepareModel()-----完成准备工作-----");
    }
}
