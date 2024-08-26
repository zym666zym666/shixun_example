package com.example.spring_mybatis.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private int id;
    private String title;
    private String content;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date date;
}
