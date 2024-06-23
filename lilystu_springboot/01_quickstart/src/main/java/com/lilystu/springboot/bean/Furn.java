package com.lilystu.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "furn01")
//@ToString//在编译时生成toString方法，默认生成无参构造器
@Data
@NoArgsConstructor//生成无参构造器,不希望其他含参构造器覆盖掉无参构造器就可以使用这个注解，没有无参构造器会影响容器注入
@AllArgsConstructor//生成全参构造器
public class Furn {
    private Integer id;
    private String name;
    private Double price;

//    public Integer getId() {
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public Double getPrice() {
//        return price;
//    }
//    public void setPrice(Double price) {
//        this.price = price;
//    }

}
