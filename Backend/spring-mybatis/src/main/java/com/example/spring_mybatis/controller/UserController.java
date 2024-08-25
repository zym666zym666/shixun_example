package com.example.spring_mybatis.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.spring_mybatis.pojo.Users;
import com.example.spring_mybatis.service.UserService;
import com.example.spring_mybatis.utils.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@RestController
public class UserController {
   @Autowired
    private UserService userService;
    //    验证码
    private LineCaptcha lineCaptcha;
    @GetMapping("/api/captcha")
    public void captcha(HttpServletResponse response){
//        创建验证码
        lineCaptcha= CaptchaUtil.createLineCaptcha(125,45);
//        设置验证码背景颜色
        lineCaptcha.setBackground(new Color(191,238,218));
//        通过写入流输出到浏览器
        try {
            ServletOutputStream os=response.getOutputStream();
            lineCaptcha.write(os);
            os.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @RequestMapping("/api/login")
    public R login(@RequestBody Map<String,String>map){
        boolean check=lineCaptcha.verify(map.get("code"));
        if(check){
            Users users=userService.login(map.get("username"),map.get("password"));
            return users!=null ?R.success(users):R.fail("用户名或密码错误");
        }else{
            return R.fail("验证码校验失败");
        }
    }
}
