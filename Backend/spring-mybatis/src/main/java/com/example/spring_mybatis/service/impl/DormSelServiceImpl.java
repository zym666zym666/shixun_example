package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.DormSelMapper;
import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.DormSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DormSelServiceImpl implements DormSelService {
    @Autowired
    private DormSelMapper dormSelMapper;

    @Override
    public int stay(DormSel dormSel) {
        return dormSelMapper.stay(dormSel);
    }

    @Override
    public Student getMessage(String phone) {
        return dormSelMapper.getMessage(phone);
    }

    @Override
    public List<DormSel> getDorm(String buildingId) {
        return dormSelMapper.getDorm(buildingId);
    }

    @Override
    public int isStay(String phone) {
        return dormSelMapper.isStay(phone);
    }

    @Override
    public List<String> getDormId() {
        return dormSelMapper.getDormId();
    }

    @Override
    public List<Map<String,Object>> getAllDormId() {
        return dormSelMapper.getAllDormId();
    }

    @Override
    public int addDorm(String buildingId) {
        return dormSelMapper.addDorm(buildingId);
    }

    @Override
    public int deleteDorm(String buildingId) {
        return dormSelMapper.deleteDorm(buildingId);
    }

    @Override
    public DormSel checkOut(String phone) { return  dormSelMapper.checkOut(phone);}

    @Override
    public int isHave(String buildingId) {
        return dormSelMapper.isHave(buildingId);
    }
    @Override
    public int Delete(int[] buildingId) {
        return dormSelMapper.Delete(buildingId);
    }
    @Override
    public int setInfo(Student student){
        return dormSelMapper.setInfo(student);
    }

    @Override
    public int updateS(String phone, String tel) {
        return dormSelMapper.updateS(phone,tel);
    }

}
