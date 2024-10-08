package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.Student;

import java.util.List;

//业务逻辑接口层
public interface StudentService {
    public int register(String tel, String password);
    public Student login(String tel, String password);
    public int checkTel(String tel);
    public int datasel(Student student);
    public Student getInfo(String tel);
    public int insertDate(String tel);
    public int getCount();
    public int getBatch();
    public List getInsCnt();
    public float getRate();
    public Integer queryId(String phone);
    public List getRen();
    public int updateTel(String tel,String phone);
}
