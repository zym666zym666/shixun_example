package com.example.spring_mybatis.mapper;


import com.example.spring_mybatis.pojo.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {
    //查询所有课程信息
    public List<Course> queryall();
    //插入某课程id,检查该课程是否可选（容量是否已满）,可选返回1，不可选返回0
    public int isCourseFull(int course_id);
    //根据课程id增加课程已选人数
    public  void courseInsert(int course_id);
    //根据课程id减少课程已选人数
    public void courseDrop(int course_id);
    //根据学生id查询出其选择的所有课程信息
    public List<Course> queryBasedOnStudentID(int student_id);

    //后台管理系统
    //添加课程信息
    public int  insert(Course course);
    //查询所有课程信息
    public List<Course> query();
    //修改课程信息
    public int update(Course course);
    //删除课程信息
    public int delete(Integer id);
    //批量删除
    public int Delete(int[] ids);
    //检查课程是否存在
    public int isRepeated(Course course);

    //查询课程库
    public List<Course> searchCourse(Integer offset);
    //更新课程介绍
    public  List<Map<String,Object>> idSearch(int id);

}
