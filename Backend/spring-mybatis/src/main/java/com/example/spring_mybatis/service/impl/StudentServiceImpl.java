package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.StudentMapper;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List getInsCnt() {
        List<Map<String,Object>> rows= studentMapper.getInsCnt();
        // 将 student_count 从 Object 转换为 int 并返回新的列表
        return rows.stream().map(row -> {
            row.put("student_count", ((Number) row.get("student_count")).intValue());
            return row;
        }).collect(Collectors.toList());
    }

    @Override
    public float getRate() {
        BigDecimal ratio =studentMapper.getRate();
        float ratioFloat = ratio.floatValue();
        return ratioFloat;
    }

    @Override
    public Integer queryId(String phone)
    {
        return studentMapper.queryId(phone);
    }

    @Override
    public List getRen() {
        return studentMapper.getRen();
    }

    @Override
    public int updateTel(String tel,String  phone) {
        return studentMapper.updateTel(tel,phone);
    }

}
