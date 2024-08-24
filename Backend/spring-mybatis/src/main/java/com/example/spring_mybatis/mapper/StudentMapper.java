package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//数据访问持久层
@Mapper
public interface StudentMapper {
//    账户激活
    public int register(String tel, String password);
//    登录
    public Student login(String tel, String password);
//    查找手机号
    public int checkTel(String tel);
//    信息收集
    public int datasel(Student student);
//    查询信息
    @Select("SELECT * FROM t_student WHERE tel = #{tel}")
    public Student getInfo(String tel);
}
