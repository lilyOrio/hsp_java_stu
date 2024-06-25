package com.lilystu.springboot.config;

import com.lilystu.springboot.bean.Car;
import com.lilystu.springboot.bean.Monster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
//proxyBeanMethods = false 表示此类中获取到的bean对象是多例模式--lite模式
public class WebConfig {

    //注入一个bean
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                //注册自定义转换器，即添加到converters容器中
                //converters 底层就是一个concurrentHashMap，内置有124个转换器
                registry.addConverter(new Converter<String, Car>() {
                //1.new Converter<String, Car>() String 转 Car
                //2.如果没有这个转换器，前台直接提交数据，就会包类型异常
                //3.如何找到这个转换器，通过key，每个转换器都有一个key,
                // key是根据原类型和目标类型来命名的，转换类型相同会覆盖掉之前添加的容器
                // 客户端的name可以找到monster中对应字段的属性，就可以找到它是需要转换成Car对象的
                    @Override
                    public Car convert(String source) {
                        //source 就是传入的字符串，传入字符串 自行车
                        //自定义转换器业务代码
                        if (!ObjectUtils.isEmpty(source)){
                            String[] split = source.split(",");
                            Car car = new Car();
                            car.setName(split[0]);
                            car.setPrice(Double.parseDouble(split[1]));
                            return car;
                        }
                        return null;
                    }
                });
                //还可以添加更多的转换器...
                registry.addConverter(new Converter<String, Monster>() {
                    @Override
                    public Monster convert(String s) {
                        return null;
                    }
                });
            }
        };
    }
}
