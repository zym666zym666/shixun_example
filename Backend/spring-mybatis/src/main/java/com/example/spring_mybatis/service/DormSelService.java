package com.example.spring_mybatis.service;

import com.cquetc.student_backed.pojo.DormSel;
import com.cquetc.student_backed.pojo.Student;

import java.util.List;

public interface DormSelService {
    public int stay(DormSel dormSel);
    Student getMessage(String phone);
    List<DormSel> getDorm(String buildingId);
    int isStay(String phone);
}
