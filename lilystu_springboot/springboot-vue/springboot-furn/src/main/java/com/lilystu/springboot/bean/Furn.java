package com.lilystu.springboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@TableName("furn")
public class Furn {
    @TableId(type = IdType.AUTO)//设置id自增长
    private Integer id;
    @NotEmpty(message = "请输入家居名")
    private String name;
    @NotEmpty(message = "请输入厂家名")
    private String maker;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "价格不能小于 0")
    private BigDecimal price;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "销量不能小于 0")
    private Integer sales;
    @NotNull(message = "请输入数字")
    @Range(min = 0, message = "库存不能小于 0")
    private Integer stock;
}

