package com.example.spring_mybatis.pojo;

import lombok.Data;

@Data
public class Course {
    private String courseName;
    private String coursePlace;
    private String courseTeacher;
    private int courseId;
    private String courseBeginTime;
    private String courseEndTime;
    private int courseCapacity;
    private int courseNumber;
}
