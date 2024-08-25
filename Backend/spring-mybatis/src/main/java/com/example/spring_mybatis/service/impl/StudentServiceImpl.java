package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.StudentMapper;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//业务逻辑接口层实现，调用数据持久层，实现对数据操作
@Service
public class StudentServiceImpl implements StudentService {
//    注入持久层实例对象
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public int register( String tel, String password) {
        return studentMapper.register(tel,password);
    }

    @Override
    public Student login( String tel, String password) {

        return studentMapper.login(tel, password);
    }

    @Override
    public int checkTel(String tel) {
        return studentMapper.checkTel(tel) ;
    }
    @Override
    public int datasel(Student student){
        insertDate(student.getTel());
        return studentMapper.datasel(student);
    }
    @Override
    public Student getInfo(String tel){
        return studentMapper.getInfo(tel);
    }

    @Override
    public int insertDate(String tel){
        return studentMapper.insertDate(tel);
    }
    @Override
    public int getCount(){
        return studentMapper.getCount();
    }

    @Override
    public int getBatch() {
        return studentMapper.getBatch();
    }

}
