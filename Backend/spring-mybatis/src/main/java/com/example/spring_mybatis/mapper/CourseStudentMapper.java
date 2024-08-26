package com.example.spring_mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseStudentMapper {
    //根据学生id和课程id检查学生是否选择了该门课程,1代表选择,0代表未选择
    public int isSelectTheCourse(int course_id,int student_id);
    //记录学生的选课信息
    public void courseInsert(int course_id,int student_id);
    //删除学生的选课信息
    public void courseDelete(int course_id,int student_id);
}
