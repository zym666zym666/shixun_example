package com.example.spring_mybatis.service;

import com.example.spring_mybatis.pojo.Course;

import java.util.List;

public interface CourseService {
    public List<Course> queryall();
    public int isCourseFull(int course_id);
    public void courseInsert(int course_id);
    public void courseDrop(int course_id);
    public List<Course> queryBasedOnStudentID(int student_id);
}
