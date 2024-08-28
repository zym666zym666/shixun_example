package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.Course;

import java.util.List;

public interface CourseService {
    public List<Course> queryall();
    public int isCourseFull(int course_id);
    public void courseInsert(int course_id);
    public void courseDrop(int course_id);
    public List<Course> queryBasedOnStudentID(int student_id);

    //添加课程信息
    int insert(Course course);
    //查询所有课程信息
    List<Course> query();
    //修改课程信息
    int update(Course course);
    //删除课程
    int delete(Integer id);
    //批量删除
    public int Delete(int[] ids);
}
