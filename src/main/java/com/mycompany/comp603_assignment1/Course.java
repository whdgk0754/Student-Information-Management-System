/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author jonghapark
 */
public class Course {
    private String courseName;
    private String courseID;
    private int credit;
    /**
     * @return the courseName
     */
    
    // 기본 생성자
    public Course() {
    }

    // 매개변수를 받는 생성자
    public Course(String courseID, String courseName, int credit) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credit = credit;
    }
    
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    
    
    
}
