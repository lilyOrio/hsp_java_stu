package com.lily_stu.web.datavalid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String save(Monster monster) {
        //注意观察输出内容
        System.out.println("monster= " + monster);
        return "datavalid/success";
    }
}
