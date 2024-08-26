package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface NoticeService {
    //    增删改查信息
    int insert(Notice notice);
    int update(Notice notice);
    int delete(Integer id);
    List<Notice> query();

    public List<Notice> showNotice();
    public Notice showBasedOnId(int id);
    public List<Notice> showNoticelimit();
}