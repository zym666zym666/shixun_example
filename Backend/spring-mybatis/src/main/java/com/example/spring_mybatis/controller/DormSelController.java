package com.cquetc.student_backed.controller;

import com.cquetc.student_backed.pojo.DormSel;
import com.cquetc.student_backed.pojo.Student;
import com.cquetc.student_backed.service.DormSelService;
import com.cquetc.student_backed.utils.R;
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
