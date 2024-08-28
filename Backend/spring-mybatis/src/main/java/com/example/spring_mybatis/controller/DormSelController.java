package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.DormSel;
import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.DormSelService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

    @GetMapping("/getDormId")
    public R getDormId(){
        List<String> id=dormSelService.getDormId();
        return id!=null?R.success(id):R.fail("操作失败");
    }

    //查询全部宿舍门牌号
    @GetMapping("api/getAllDormId")
    public R getAllDorm(){
        List <Map<String,Object>> dors=dormSelService.getAllDormId();
        return  dors!=null?R.success(dors):R.fail("操作失败");
    }

    //添加宿舍
    @PostMapping("api/addDorm")
    public R addDorm(String buildingId){
        int isHave=dormSelService.isHave(buildingId);
        if(isHave==0){
            dormSelService.addDorm(buildingId);
            return R.success(isHave);
        }
        else {
            return R.fail("宿舍已存在");
        }
    }

    //删除宿舍
    @PostMapping("api/deleteDorm")
    public R deleteDrom(String buildingId){
        int isDelete=dormSelService.deleteDorm(buildingId);
        return isDelete>0?R.success(isDelete):R.fail("操作失败");
    }

    // 退宿舍
    @PostMapping("/checkOut")
    public R checkOut( String phone) {
        DormSel dormSel = dormSelService.checkOut(phone);
        return dormSel != null ? R.fail("操作失败") : R.success(dormSel);
    }

    //批量删除
    @PostMapping("api/DeleteDorms")
    public R Delete(int[] buildingId){
        int row=dormSelService.Delete(buildingId);
        return row>0 ?R.success(row):R.fail("操作失败");
    }
}
