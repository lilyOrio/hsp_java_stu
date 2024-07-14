package com.lilystu.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("furn")
public class Furn {
    @TableId(type = IdType.AUTO)//设置id自增长
    private Integer id;
    private String name;
    private String maker;
    private Double price;
    private Integer sales;
    private Integer stock;
}

