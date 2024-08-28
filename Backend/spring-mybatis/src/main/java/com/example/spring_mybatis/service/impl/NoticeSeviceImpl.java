package com.example.spring_mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.spring_mybatis.mapper.NoticeMapper;
import com.example.spring_mybatis.pojo.Notice;
import com.example.spring_mybatis.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class NoticeSeviceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public int insert(Notice notice) {
        Date date=new Date();
        notice.setDate(date);
        return noticeMapper.insert(notice);
    }

    @Override
    public int update(Notice notice) {
        Date date=new Date();
        notice.setDate(date);
        return noticeMapper.update(notice);
    }

    @Override
    public int delete(Integer id) {
        return noticeMapper.delete(id);
    }



    @Override
    public List<Notice> getNotice(String title) {
        return noticeMapper.getNotice(title);
    }

    @Override
    public List<Notice> showNotice() {
        return noticeMapper.showNotice();
    }

    @Override
    public Notice showBasedOnId(int id) {
        return noticeMapper.showBasedOnId(id);
    }

    @Override
    public List<Notice> showNoticelimit() {
        return noticeMapper.showNoticelimit();
    }

    @Override
    public int Delete(int[] ids) {
        return noticeMapper.Delete(ids);
    }

    @Override
    public IPage<Notice> selectPageVo(IPage<?> page, Wrapper wrapper) {
        return noticeMapper.selectPageVo(page,wrapper);
    }
}
