package com.example.spring_mybatis.controller;

import com.example.spring_mybatis.pojo.Course;
import com.example.spring_mybatis.service.CourseService;
import com.example.spring_mybatis.service.CourseStudentService;
import com.example.spring_mybatis.service.StudentService;
import com.example.spring_mybatis.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    //实例化CourseService
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private StudentService studentService;

    //查询所有课程信息
    @GetMapping("/course")
    public R queryall()
    {
        List<Course> courses=courseService.queryall();
        return courses!=null ? R.success(courses):R.fail("操作失败");
    }

    //选课功能
    @PostMapping("/SelectCourse")
    public R courseSelection(@RequestParam("phone") String phone, @RequestParam("courseId") Integer courseId)
    {
        System.out.println(phone+" "+courseId);
        //根据电话号码查询学生id,并将其保存在res中
        Integer res=studentService.queryId(phone);
        int rst=courseService.isCourseFull(courseId);
        //当学生id非空，即学生存在时
        if(res!=null)
        {
            if(rst==1)
            {
                //查询学生是否选择该门课程
                int ret=courseStudentService.isSelectTheCourse(courseId,res);
                //学生未选择该门课程时
                if(ret==0)
                {
                    //增加课程选课人数
                    courseService.courseInsert(courseId);
                    //记录学生的选课信息
                    courseStudentService.courseInsert(courseId,res);
                    return R.success(1);
                }
                else
                {
                    return R.success(0);
                }
            }
            else
            {
                return R.success(2);
            }
        }
        return R.fail("操作失败");
    }
    //退课功能
    @PostMapping("/Dropcourse")
    public R course_drop(Integer courseId,String phone)
    {
        System.out.println(courseId+" "+phone);
        Integer res=studentService.queryId(phone);
        if(res!=null)
        {
            int ret=courseStudentService.isSelectTheCourse(courseId,res);
            if(ret==1)
            {
                courseService.courseDrop(courseId);
                courseStudentService.courseDelete(courseId,res);
                return R.success(1);
            }
            else
            {
                return R.success(0);
            }
        }
        return R.fail("操作失败");

    }

    //显示我的课程
    @GetMapping("/Mycourse")
    public R Query(String phone)
    {
        Integer res=studentService.queryId(phone);
        if(res!=null)
        {
            List<Course> courses=courseService.queryBasedOnStudentID(res);
            if(courses!=null)
            {
                return R.success(courses);
            }
            else
            {
                return R.fail("操作失败");
            }
        }
        return R.fail("操作失败");
    }


    //后台管理系统
    //新增课程
    @PostMapping("/api/insertCourse")
    public R addCourse(@RequestBody Course course)
    {
        System.out.println(course);
        int ret=courseService.isRepeated(course);
        if(ret!=0) return R.success(20);
        int row=courseService.insert(course);
        return row>0?R.success(row):R.fail("操作失败");
    }
    //查询所有课程
    @GetMapping("/api/allCourse")
    public R queryAllCourse()
    {
        List<Course> courses=courseService.query();
        return courses!=null?R.success(courses):R.fail("操作失败");
    }
    //修改课程
    @PostMapping("/api/updateCourse")
    public R update(@RequestBody Course course)
    {
        int rst=courseService.isRepeated(course);
        if(rst!=0) return R.success(20);
        int ret=courseService.update(course);
        return ret!=0?R.success(ret):R.fail("操作失败");
    }
    //删除课程
    @PostMapping("/api/deleteCourse")
    public R deleteCourse(Integer id){

        int row = courseService.delete(id);
        return row > 0?R.success(row):R.fail("操作失败");
    }
    //批量删除课程
    @PostMapping("/api/DeleteCourses")
    public R Delete(int[] ids)
    {
        int row=courseService.Delete(ids);
        return row>0?R.success(row):R.fail("操作失败");
    }
    @PostMapping("/searchCourse")
    public R searchCourse (Integer coursepage){
        List<Course> courses= courseService.searchCourse(coursepage);
        return courses!=null?R.success(courses):R.fail("操作失败");
    }

    @PostMapping("/idSearch")
    public R idSearch (@RequestParam int id){
        List<Map<String,Object>> res= courseService.idSearch(id);
//        HashMap<String,Object> map = new HashMap<>();
        return res!=null?R.success(res):R.fail("操作失败");
    }
}
