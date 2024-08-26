package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.Notice;
import com.example.spring_mybatis.service.NoticeService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("/api/notice")
    public R addNotice(@RequestBody Notice notice) {
        int row =noticeService.insert(notice);
        return row>0?R.success(row):R.fail("操作失败");
    }
    @PutMapping("/api/notice")
    public R updateNotice(@RequestBody Notice notice) {
        int row =noticeService.update(notice);
        return row>0?R.success(row):R.fail("操作失败");
    }
    @DeleteMapping("/api/notice/{id}")
    public R deleteNotice(@PathVariable Integer id) {
        int row =noticeService.delete(id);
        return row>0?R.success(row):R.fail("操作失败");
    }
    @GetMapping("/api/notice")
    public R queryAll(){
        List<Notice> noticeList=noticeService.query();
        return noticeList.size()>0?R.success(noticeList):R.fail("操作失败");
    }

}
