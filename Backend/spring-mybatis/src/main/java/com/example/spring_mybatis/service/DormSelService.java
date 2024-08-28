package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;

import java.util.List;
import java.util.Map;

public interface DormSelService {
    public int stay(DormSel dormSel);
    Student getMessage(String phone);
    List<DormSel> getDorm(String buildingId);
    int isStay(String phone);
    List<String> getDormId();
    List<Map<String,Object>> getAllDormId();
    int addDorm(String buildingId);
    int deleteDorm(String buildingId);
    DormSel checkOut(String phone);
}
