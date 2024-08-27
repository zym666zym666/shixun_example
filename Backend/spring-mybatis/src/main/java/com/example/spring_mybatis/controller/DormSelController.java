package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.DormSelService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class DormSelController {
    @Autowired
    private DormSelService dormSelService;

    //入住宿舍
    @PostMapping("/stay")
    public R stay(@RequestBody DormSel dormSel){
        int isStays=dormSelService.isStay(dormSel.getStuPhone());
        if (isStays==0){
            dormSelService.stay(dormSel);
            return R.success(dormSel);
        }
        else{
            return R.fail("操作失败");
        }
    }

    //查询学生信息
    @GetMapping("/getStu")
    public R getMessage(String phone){
        Student stu=dormSelService.getMessage(phone);
        return stu!=null?R.success(stu):R.fail("操作失败");
    }

    //查询宿舍信息
    @GetMapping("/getDorm")
    public R getDorm(String buildingId){
        List <DormSel> dor=dormSelService.getDorm(buildingId);
        return  dor!=null?R.success(dor):R.fail("操作失败");
    }

}