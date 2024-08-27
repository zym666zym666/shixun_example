package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.DormInfoMapper;
import com.example.spring_mybatis.service.DormInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DormInfoServiceImpl implements DormInfoService {
    @Autowired
    DormInfoMapper dormInfoMapper;

    @Override
    public  Map<String, Object> getDormInfo() {
        return (Map<String, Object>) dormInfoMapper.getDormInfo();
    }

    @Override
    public List<Map<String, Object>> getBuildId() {
        return dormInfoMapper.getBuildId();
    }


}
