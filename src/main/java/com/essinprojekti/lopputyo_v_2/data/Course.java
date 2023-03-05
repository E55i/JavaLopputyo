package com.essinprojekti.lopputyo_v_2.data;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int courseId;
    private String name;
    private List<Student> courseStudents = new ArrayList<>();
    private int credits;

    public Course(int courseId, String name, int credits){
        this.courseId=courseId;
        this.name=name;
        this.credits=credits;
    }

    public Course(){
        this(0,"",0);

    }

    public void setCourseId(int courseId){
        this.courseId=courseId;
    }

    public int getCourseId(){
        return this.courseId;
    }
    
    public void setName (String name){
        this.name=name;
    }

    public String getName (){
        return this.name;
    }

    public void setCourseStudents (Student student){
        courseStudents.add(student);
    }

    public List<Student> getCourseStudents(){
        return courseStudents;
         
    }

    public void setCredits (int credits){
        this.credits=credits;
    }

    public int getCredits (){
        return this.credits;
    }
    
}
