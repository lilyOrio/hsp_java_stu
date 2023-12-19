package com.lily_stu.web.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsHandler {
    @RequestMapping(value = "/buy")
    public String buy() {
        System.out.println("=======buy()=====");
        return "lilyView";
    }

    /**
     * 在目标方法直接指定重定向&请求转发
     * @return
     */
    @RequestMapping(value = "/order")
    public String order() {
        System.out.println("=======order()=====");
    //这里直接指定转发到哪个页面
        return "forward:/WEB-INF/pages/my_view.jsp";
    //重定向, 如果是重定向，就不能重定向到/WEB-INF 目录中
//        return "redirect:/login.jsp";
    }
}
