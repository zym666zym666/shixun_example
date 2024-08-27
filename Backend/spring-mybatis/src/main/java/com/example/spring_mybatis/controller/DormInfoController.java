package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.service.DormInfoService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DormInfoController {

    @Autowired
    private DormInfoService dormInfoService;

    @GetMapping("/getDormInfo")
    public R getDormInfo() {
        Map<String, Object> dormInfo = dormInfoService.getDormInfo();
        return dormInfo != null ? R.success(dormInfo) : R.fail("操作失败");
    }

    @GetMapping("/getBuildId")
    public R getBuildId() {
        List<Map<String, Object>> buildId = dormInfoService.getBuildId();
        return buildId != null ? R.success(buildId) : R.fail("操作失败");
    }

}


