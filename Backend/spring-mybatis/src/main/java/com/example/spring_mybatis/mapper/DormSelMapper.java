package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface DormSelMapper {
    public int stay(DormSel dormSel);
    public Student getMessage(String phone);
    public List<DormSel> getDorm(String buildingId);
    public int isStay(String stuPhone);
    public List<String> getDormId();
    public List<Map<String,Object>> getAllDormId();
    public int addDorm(String buildingId);
    public int deleteDorm(String buildingId);
    public DormSel checkOut(String phone);
    public int isHave(String buildingId);
    public int Delete(int[] buildingId);
    public int setInfo(Student student);
    int updateS(String phone, String tel);
}
