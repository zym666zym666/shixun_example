package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.UserMapper;
import com.example.spring_mybatis.pojo.Users;
import com.example.spring_mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Users login(String username, String password) {
        return userMapper.login(username, password);
    }
}
