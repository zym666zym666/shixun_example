package com.example.spring_mybatis.controller;

import ch.qos.logback.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.spring_mybatis.pojo.Notice;

import com.example.spring_mybatis.pojo.QueryPageParam;
import com.example.spring_mybatis.service.NoticeService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @PostMapping("/api/noticeList")
    public R queryAll(@RequestBody QueryPageParam pageParam) {
        System.out.println(pageParam.getPageNum() + "-" + pageParam.getPageSize());
       HashMap param = pageParam.getParam();
        String title = (String) param.get("title");
        System.out.println("title:" + title);
        IPage<Notice> iPage = new Page<>(pageParam.getPageNum(),pageParam.getPageSize());
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        // 如果name不为空，则添加查询条件
        if (!StringUtil.isNullOrEmpty(title)) {
            wrapper.like(Notice::getTitle, title);
        }
        IPage<Notice> pages = noticeService.selectPageVo(iPage, wrapper);

        return R.success(pages);
    }

    //根据标题查询信息
    @GetMapping("api/getNotice")
    public R getNotice(@RequestParam String title){
        List<Notice> notices=noticeService.getNotice(title);
        System.out.println(title);
        return notices!=null?R.success(notices):R.fail("操作失败");
    }

    //查询所有通知
    @GetMapping("/notice")
    public R showNotice()
    {
        List<Notice> notices=noticeService.showNotice();
        return notices!=null?R.success(notices):R.fail("操作失败");
    }
    //根据id查询通知
    @GetMapping("/noticeid")
    public R showBaesdOnId(int id)
    {
        Notice notice=noticeService.showBasedOnId(id);
        return notice!=null?R.success(notice):R.fail("操作失败");
    }
    //查询最新的消息
    @GetMapping("/noticelimit")
    public R showNoticelimit()
    {
        List<Notice> notices=noticeService.showNoticelimit();
        return notices!=null?R.success(notices):R.fail("操作失败");
    }

    @PostMapping("/api/Delete")
    public R Delete(int[] ids)
    {
        System.out.println(ids);
        int row=noticeService.Delete(ids);
        System.out.println(row);
        return row!=0?R.success(row):R.fail("操作失败");
    }

}
