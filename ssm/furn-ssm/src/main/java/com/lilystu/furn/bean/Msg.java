package com.lilystu.furn.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来返回json 的数据的通用类
 */
public class Msg {
    // 状态码，200 成功，400 失败
    private int code;
    // 信息
    private String msg;
    // 返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    //返回success对应的Msg
    public static Msg success() {
        Msg res = new Msg();
        res.setCode(200);
        res.setMsg("success");
        return res;
    }

    //返回fail对应的Msg
    public static Msg fail() {
        Msg res = new Msg();
        res.setCode(400);
        res.setMsg("fail");
        return res;
    }

    //给Msg对象设置数据
    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

}
