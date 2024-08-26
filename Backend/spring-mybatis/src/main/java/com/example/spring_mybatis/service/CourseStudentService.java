package com.example.spring_mybatis.service;

public interface CourseStudentService {
    public int isSelectTheCourse(int course_id,int student_id);
    public void courseInsert(int course_id,int student_id);
    public void courseDelete(int course_id,int student_id);
}
