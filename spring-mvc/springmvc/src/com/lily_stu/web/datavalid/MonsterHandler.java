package com.lily_stu.web.datavalid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Scope(value = "prototype")
public class MonsterHandler {
    //显示添加monster 的界面
    @RequestMapping(value = "/addMonsterUI")
    public String addMonsterUI(Map<String, Object> map){
        /*老韩解读:
         1. 这里的表单，我们使用springMVC 的标签来完成
         2. SpringMVC 表单标签在显示之前必须在request 中有一个bean, 该bean的属性和表单标签的字段要对应!
            request 中的key 为: form 标签的modelAttrite 属性值， 比如这里的monsters
         3. SpringMVC 的form:form 标签的action 属性值中的/ 不代表WEB 应用的根目录.
         4. <form:form action="monster" method="POST" modelAttribute="monster">
         // 这里需要给request 增加一个monster ， 因为jsp 页面的modelAttribute="monster"需要
         //这时是springMVC 的内部的检测机制即使是一个空的也需要，否则报错.
         */
        map.put("monster",new Monster());
        return "datavalid/monster_addUI";
    }

    //处理添加，更加请求的方法来确定
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Monster monster, Errors errors, Map<String,Object> map,String name) {
        //注意观察输出内容
        System.out.println("monster= " + monster);
        System.out.println("name= " + name);//输出的是初始化name值
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey()+"\n" + "value = " + entry.getValue());
        }
        if (errors.hasErrors()){
            System.out.println("验证出错!");
            for (ObjectError error : errors.getAllErrors()) {
                System.out.println(error);
            }
            //返回添加界面
            return "/datavalid/monster_addUI";
        }
        return "datavalid/success";
    }

    /**
     * 取消某个属性的绑定
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
    //1. setDisallowedFields() 是可变形参，可以指定多个字段
    //2. 当将一个字段/属性，设置为disallowed,就不在接收表单提交的值
    //3. 那么这个字段/属性的值，就是该对象默认的值(具体看程序员定义时指定)
    //4. 一般来说，如果不接收表单字段提交数据，则该对象字段的验证也就没有意义了
    // ,可以注销掉，比如注销//@NotEmpty
    // //@NotEmpty
    // private String name;
    //测试完，记得注销了.
//        dataBinder.setDisallowedFields("name");
    }
}
