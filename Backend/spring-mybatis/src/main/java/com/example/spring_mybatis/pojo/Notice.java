package com.example.spring_mybatis.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private int id;
    private String title;
    private String content;
    private Date date;
}