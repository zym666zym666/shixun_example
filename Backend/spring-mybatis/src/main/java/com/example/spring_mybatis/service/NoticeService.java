package com.example.spring_mybatis.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.spring_mybatis.pojo.Notice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {
    //    增删改查信息
    int insert(Notice notice);
    int update(Notice notice);
    int delete(Integer id);
    public PageInfo query(Integer currentPage, Integer pageSize);
    public List<Notice> getNotice(String title);
    public List<Notice> showNotice();
    public Notice showBasedOnId(int id);
    public List<Notice> showNoticelimit();
    public int Delete(int[] ids);

    IPage<Notice> selectPageVo(IPage<?> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}