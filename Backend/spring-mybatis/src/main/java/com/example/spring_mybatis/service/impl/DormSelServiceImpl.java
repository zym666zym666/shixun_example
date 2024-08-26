package com.cquetc.student_backed.service.impl;

import com.cquetc.student_backed.mapper.DormSelMapper;
import com.cquetc.student_backed.pojo.DormSel;
import com.cquetc.student_backed.pojo.Student;
import com.cquetc.student_backed.service.DormSelService;
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
