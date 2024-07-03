package com.lilystu.springboot.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Monster {
    private Integer id;
    private Integer age;
    private String name;
    private String email;

    //解决时区问题 GMT格林尼治
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday;
    private double salary;
    private Integer gender;
}
