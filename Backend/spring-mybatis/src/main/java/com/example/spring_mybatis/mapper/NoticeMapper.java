package com.example.spring_mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.spring_mybatis.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
//    增删改查信息
//    public int insert(Notice notice);
    public int update(Notice notice);
    public int delete(Integer id);
//    public List<Notice> query(Integer pageSize, Integer offset);


    //查询所有信息
    public List<Notice> showNotice();
    //根据通知id查询指定的通知
    public Notice showBasedOnId(int id);
    //查询有限条信息
    public  List<Notice> showNoticelimit();
    //根据标题查通知
    public List<Notice> getNotice(String title);
    //批量删除
    public  int Delete(int[] ids);

    public List<Notice> query();

    IPage<Notice> selectPageVo(IPage<?> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
