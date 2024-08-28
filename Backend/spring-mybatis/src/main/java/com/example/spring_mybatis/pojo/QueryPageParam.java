package com.example.spring_mybatis.pojo;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;

    private HashMap param = new HashMap();



    public static void main(String[] args) {

        QueryPageParam q1 = new QueryPageParam();
        QueryPageParam.PAGE_SIZE = 40;

        QueryPageParam q2 = new QueryPageParam();
        System.out.println(q2.getPageSize());


    }

}
