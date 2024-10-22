package com.example.spring_mybatis.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 熊初墨
 * @CreateTime: 2023-08-28  16:08
 * @Description: 封装统一返回结果
 * @Version: 1.0
 */
@Data
public class R implements Serializable {
    //响应状态码
    private int code;
    //消息提示
    private String msg;
    //响应数据
    private Object data;

    //执行成功
    public static R success(Object data){
        return ok(200,"请求成功",data);
    }


    //执行失败
    public static R fail(String msg) {
        return err(1001,"操作失败");
    }

    public static R ok(int code, String msg, Object data) {
        R r= new R();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static R err(int code,String msg) {
        R r= new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
