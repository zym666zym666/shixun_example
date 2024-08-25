package com.example.spring_mybatis.pojo;

import lombok.Data;

import java.time.Period;
import java.util.Date;
@Data
public class Users {
    private int userId;
    private String userName;
    private String password;
    private String nickname;
    private String email;
    private int status;
    private Date createTime;
}
