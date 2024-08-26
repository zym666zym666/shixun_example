package com.example.spring_mybatis.mapper;

import com.cquetc.student_backed.pojo.DormSel;
import com.cquetc.student_backed.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DormSelMapper {
    int stay(DormSel dormSel);
    Student getMessage(String phone);
    List<DormSel> getDorm(String buildingId);
    int isStay(String stuPhone);

}
