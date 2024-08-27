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

    //查询所有信息
    public List<Notice> showNotice();
    //根据通知id查询指定的通知
    public Notice showBasedOnId(int id);
    //查询有限条信息
    public  List<Notice> showNoticelimit();
    //根据标题查通知
    public List<Notice> getNotice(String title);
}
