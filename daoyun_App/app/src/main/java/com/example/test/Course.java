package com.example.test;

public class Course {
    private int imageId;
    private String courseName;
    private String teacherName;
    private String className;

    public Course(int imageId, String courseName, String teacherName, String className){
        this.imageId = imageId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.className = className;
    }

    public int getImageId(){
        return imageId;
    }

    public String getCourseName(){
        return courseName;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public String getClassName(){
        return className;
    }
}
