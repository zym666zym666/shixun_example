package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.Student;
import com.example.spring_mybatis.service.StudentService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//控制类 接收前端发送的请求并处理请求，调用业务逻辑接口层，返回json格式
@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
}
