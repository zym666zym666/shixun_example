package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.Users;
import org.apache.ibatis.annotations.Mapper;

//用户数据访问持久层
@Mapper
public interface UserMapper {
//    用户登录
    Users login(String username, String password);

}
