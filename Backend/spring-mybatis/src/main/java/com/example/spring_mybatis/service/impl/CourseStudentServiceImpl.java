package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.CourseStudentMapper;
import com.example.spring_mybatis.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseStudentServiceImpl implements CourseStudentService {
    //实例化CourseStudentMapper
    @Autowired
    private CourseStudentMapper courseStudentMapper;
    //重写CourseStudentService接口中的方法
    @Override
    public int isSelectTheCourse(int course_id,int student_id) {
        return courseStudentMapper.isSelectTheCourse(course_id,student_id);
    }
    @Override
    public void courseInsert(int course_id,int student_id)
    {
        courseStudentMapper.courseInsert(course_id,student_id);
    }
    @Override
    public void courseDelete(int course_id,int student_id)
    {

        courseStudentMapper.courseDelete(course_id,student_id);
    }
}
