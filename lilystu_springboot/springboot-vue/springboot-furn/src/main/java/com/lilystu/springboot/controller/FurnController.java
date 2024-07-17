package com.lilystu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.service.FurnService;
import com.lilystu.springboot.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class FurnController {

    @Autowired
    FurnService furnService;

    @PostMapping("/save")
    public Result<?> save(@Validated @RequestBody Furn furn, Errors errors) {
        Map<String, Object> map = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError e : fieldErrors) {
            map.put(e.getField(), e.getDefaultMessage());
        }
        if (map.isEmpty()) {
            furnService.save(furn);
            return Result.success();
        } else {
            return Result.error("400", "校验失败", map);
        }
    }

    @RequestMapping("/furns")
    public Result<?> listFurns() {
        List<Furn> furnList = furnService.list();
        return Result.success(furnList);
    }

    //GET 获取 、POST 创建、PUT 更新、DELETE 删除
    @PutMapping("/update")
    public Result<?> update(@RequestBody Furn furn) {
        furnService.updateById(furn);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    public Result<?> del(@PathVariable Integer id) {
        furnService.removeById(id);
        return Result.success();
    }

    @GetMapping("/find/{id}")
    public Result<?> find(@PathVariable Integer id) {
        Furn furn = furnService.getById(id);
        return Result.success(furn);
    }

    @GetMapping("/page")
    public Result<?> listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<Furn> furnPage = furnService.page(new Page<>(pageNum, pageSize));
        return Result.success(furnPage);
    }

    @GetMapping("/furnsBySearchPage")
    public Result<?> listFurnsByConditionPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "5") Integer pageSize,
                                              @RequestParam(defaultValue = "") String search) {
        QueryWrapper<Furn> queryWrapper = Wrappers.query();
        if (StringUtils.hasText(search)) {
            queryWrapper.like("name", search);
        }
        Page<Furn> furnPage = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(furnPage);
    }

    @GetMapping("/furnsBySearchPage2")
    public Result<?> listFurnsByConditionPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                               @RequestParam(defaultValue = "") String search) {

        // 先 创 建 LambdaQueryWrapper, 可 以 将 我 们 的 检 索 条 件 封 装 到 LambdaQueryWrapper
        LambdaQueryWrapper<Furn> queryWrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(search)) {
            /*
            @FunctionalInterface
            public interface SFunction<T, R> extends Function<T, R>, Serializable {
            }
            父接口
            @FunctionalInterface
            public interface Function<T, R> {
            R apply(T t);//抽象方法
            //默认实现方法。。。
            }
             */
//            SFunction<Furn, Object> sf = Furn::getName;
//            queryWrapper.like(sf, search);

            queryWrapper.like(Furn::getName, search);
        }
        Page<Furn> page = furnService.page(new Page<>(pageNum, pageSize), queryWrapper);
        log.info("page={}", page.getRecords());
        return Result.success(page);
    }
}
