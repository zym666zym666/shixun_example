package com.example.spring_mybatis.service;


import java.util.Map;

public interface DormInfoService {
    Map<String, Object> getDormInfo();
    List<String> getBuildId();

}
