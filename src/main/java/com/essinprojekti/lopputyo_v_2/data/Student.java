package com.essinprojekti.lopputyo_v_2.data;

public class Student {
    
    private String name;
    private double age;
    private int studentId;
    private int firstYear;

    public Student(String name, double age, int studentID, int firstYear) {
        this.name = name;
        this.age=age;
        this.studentId = studentID;
        this.firstYear = firstYear;
    }

    public Student() {
        this("",0, 0, 0);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getAge() {
        return this.age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getFirstYear() {
        return this.firstYear;
    }

    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

}
