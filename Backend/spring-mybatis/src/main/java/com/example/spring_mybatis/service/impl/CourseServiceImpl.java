package com.example.spring_mybatis.service.impl;

import com.example.spring_mybatis.mapper.CourseMapper;
import com.example.spring_mybatis.pojo.Course;
import com.example.spring_mybatis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    //实例化CourseMapper
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> queryall()
    {
        return courseMapper.queryall();
    }
    @Override
    public int isCourseFull(int course_id)
    {
        return courseMapper.isCourseFull(course_id);
    }
    @Override
    public void courseInsert(int course_id)
    {
        courseMapper.courseInsert(course_id);
    }
    @Override
    public void courseDrop(int course_id)
    {
        courseMapper.courseDrop(course_id);
    }
    @Override
    public List<Course> queryBasedOnStudentID(int student_id)
    {
        return courseMapper.queryBasedOnStudentID(student_id);
    }


    @Override
    public int insert(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public List<Course> query() {
        return courseMapper.query();
    }

    @Override
    public int update(Course course) {
        return courseMapper.update(course);
    }

    @Override
    public int delete(Integer id) {
        return courseMapper.delete(id);
    }

    @Override
    public int Delete(int[] ids) {
        return courseMapper.Delete(ids);
    }
}
