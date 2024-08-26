package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.StudentService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;
import java.util.Map;

//控制类 接收前端发送的请求并处理请求，调用业务逻辑接口层，返回json格式
@RestController
@CrossOrigin
public class LoginController {
//   注入业务逻辑接口层实例对象
    @Autowired
    private StudentService studentService;
//    账户激活接口
    @PostMapping("/register")
    public R register(@RequestParam String tel, @RequestParam String password){
        int register=studentService.register(tel,password);
        return register>0?R.success(register):R.fail("操作失败");
    }
    @PostMapping("/login")
    public R login(@RequestParam String tel, @RequestParam String password){
        Student student=studentService.login(tel, password);
        return student!=null?R.success(student):R.fail("操作失败");
    }
    @PostMapping("/checkTel")
    public R checkTel(@RequestParam("tel") String tel) {
        try {
           int exists = studentService.checkTel(tel);
            return R.success(exists);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常
            return R.fail("检查手机号失败");
        }
    }

    @PostMapping("/datasel")
    public R datasel(@RequestBody  Student student){
        try {
            int done = studentService.datasel(student);
            return R.success(done);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常
            return R.fail("提交信息失败");
        }
    }
    @PostMapping("/getInfo")
    public R getInfo(@RequestParam("tel") String tel) {
        try {
            Student student = studentService.getInfo(tel);
            return student != null ? R.success(student) : R.fail("信息未找到");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询信息失败");
        }
    }
//    获取当天报道人数
    @GetMapping("/getCount")
    public R getCount() {
        try {
            int count = studentService.getCount();
            return R.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询信息失败");
        }
    }
//    当前批次已报到
    @GetMapping("/getBatch")
    public R getbatch() {
        try {
            int count = studentService.getBatch();
            return R.success(count);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询信息失败");
        }
    }


//    获取各个学院人数
    @GetMapping("/getInsCnt")
    public R getInsCnt() {
        try{
            List<Map<String,Object>>  insCnt = studentService.getInsCnt();
            return R.success(insCnt);
        }catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询信息失败");
        }

    }
//    获取报到人数比例
@GetMapping("/getRate")
public R getRate() {
    try{
        float res = studentService.getRate();
        return R.success(res);
    }catch (Exception e) {
        e.printStackTrace();
        return R.fail("查询信息失败");
    }
}
}
