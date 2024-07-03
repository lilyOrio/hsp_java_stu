package com.lilystu.springboot.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
//@TableName(value = "monster_table")//当表名和类名无法匹配到时，可以使用这个注解指定表名
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