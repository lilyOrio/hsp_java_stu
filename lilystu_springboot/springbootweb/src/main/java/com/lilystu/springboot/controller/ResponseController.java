package com.lilystu.springboot.controller;

import com.lilystu.springboot.bean.Car;
import com.lilystu.springboot.bean.Monster;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ResponseController {

    //已json格式返回数据
    @GetMapping("/getMonster")
    @ResponseBody
    public Monster getMonster(){
        Monster monster = new Monster();
        monster.setId(100);
        monster.setAge(200);
        monster.setName("jack");
        monster.setBirth(new Date());
        monster.setIsMarried(true);
        Car car = new Car();
        car.setPrice(1000.0);
        car.setName("扫把");
        monster.setCar(car);
        return monster;

        /*
        AbstractJackson2HttpMessageConverter.java
        MediaType contentType = outputMessage.getHeaders().getContentType();//通过浏览器拿到返回的数据格式
        ObjectMapper objectMapper = this.selectObjectMapper(clazz, contentType);
        JsonGenerator generator = objectMapper.getFactory().createGenerator(outputStream, encoding);//获取对应的generator
        objectWriter.writeValue(generator, value);//将对象转换成对应格式
         */

        /*
        内容协商：
        服务器引入支持xml
        浏览器请求头text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,·/*;q=0.
            返回xml的权重比json高，默认返回xml格式数据，如果要返回json则可以设置format=json如：http://localhost:10001/getMonster?format=json
         */
    }
}
