package com.example.spring_mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface DormInfoMapper {
    Map<String, Object> getDormInfo();
    List<Map<String, Object>> getBuildId();

}
