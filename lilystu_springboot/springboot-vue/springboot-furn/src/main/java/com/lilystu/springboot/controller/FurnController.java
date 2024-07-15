package com.lilystu.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lilystu.springboot.bean.Furn;
import com.lilystu.springboot.service.FurnService;
import com.lilystu.springboot.utils.Result;
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
}
