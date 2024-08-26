package com.example.spring_mybatis.service.impl;


import com.example.spring_mybatis.mapper.DormSelMapper;
import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.DormSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
