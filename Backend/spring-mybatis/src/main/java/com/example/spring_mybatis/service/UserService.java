package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.pojo.Users;

//业务逻辑接口层
public interface UserService {
    Users login(String username, String password);
}
