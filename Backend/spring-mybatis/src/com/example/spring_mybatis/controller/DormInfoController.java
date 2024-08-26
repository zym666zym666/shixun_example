package com.example.spring_mybatis.controller;

import com.cquetc.student_backed.service.DormInfoService;
import com.cquetc.student_backed.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
}

