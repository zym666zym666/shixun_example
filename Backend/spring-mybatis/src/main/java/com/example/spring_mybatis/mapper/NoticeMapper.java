package com.example.spring_mybatis.mapper;

import com.example.spring_mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
//    增删改查信息
    public int insert(Notice notice);
    public int update(Notice notice);
    public int delete(Integer id);
    public List<Notice> query();
}
