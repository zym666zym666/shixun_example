package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
//数据访问持久层
@Mapper
public interface StudentMapper {
//    账户激活
    public int register(String tel, String password);
//    登录
    public Student login(String tel, String password);
//    查找手机号
    public int checkTel(String tel);
}
