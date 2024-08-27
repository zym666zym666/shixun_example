package com.example.spring_mybatis.service.impl;

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
    public List<Notice> query() {
        return noticeMapper.query();
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
}
