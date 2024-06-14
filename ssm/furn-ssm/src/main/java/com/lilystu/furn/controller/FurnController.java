package com.lilystu.furn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lilystu.furn.bean.Furn;
import com.lilystu.furn.bean.Msg;
import com.lilystu.furn.service.FurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FurnController {

    @Autowired(required = false)
    FurnService furnService;

    @PostMapping("/save")
    @ResponseBody//将返回数据转为json格式
    public Msg save(@RequestBody Furn furn) {//将前端发送的json 数据,转成JavaBean 数据
        furnService.save(furn);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping("/furns")
    public Msg listFurns() {
        List<Furn> furnList = furnService.findAll();
        return Msg.success().add("furnsList", furnList);
    }

    @PutMapping("/update")
    @ResponseBody
    public Msg update(@RequestBody Furn furn) {
        furnService.update(furn);
        return Msg.success();
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public Msg del(@PathVariable Integer id) {
        System.out.println("del id= " + id);
        furnService.del(id);
        return Msg.success();
    }

    @RequestMapping("/find/{id}")
    @ResponseBody
    public Msg find(@PathVariable Integer id) {
        System.out.println("find id= " + id);
        return Msg.success().add("furn", furnService.findById(id));
    }

    @ResponseBody
    @RequestMapping("/furnsByPage")
    public Msg listFurnsByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        //查询前，需要调用PageHelper.startPage()
        //看下源码startPage(int pageNum, int pageSize)
        PageHelper.startPage(pageNum, pageSize);
        //在PageHelper.startPage() 后调用findAll 就是分页查询(物理分页有limit)
        List<Furn> furnList = furnService.findAll();
        //分页查询完之后，可以使用pageInfo 来包装查询后的结果，
        //1. 只需要将pageInfo 交给页面就行
        //2. pageInfo 封装了详细的分页信息，包括查询出来的数据
        // 比如总共有多少页，当前是第几页等
        //3. 看源码PageInfo(List<T> list, int navigatePages)
        PageInfo<Furn> pageInfo = new PageInfo<Furn>(furnList, pageSize);
        System.out.println("pageInfo==>" + pageInfo);
        return Msg.success().add("pageInfo", pageInfo);
    }
}
