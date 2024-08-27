package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

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
//    添加报到时间
    public int insertDate(String tel);
//    获取当天报道人数
    public int getCount();
//获取当前批次报道人数
    public int getBatch();
//获取各系人数
    public List getInsCnt();
//    获取报到比例
    public BigDecimal getRate();
// 根据学生手机号查询学生id
    public Integer queryId(String phone);
//获取各日报到人数
    public List getRen();
//修改注册手机号
    public int updateTel(String tel,String phone);
}

