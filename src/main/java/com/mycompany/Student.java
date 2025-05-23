/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author jonghapark
 */
public class Student {
    private String studentID; //Primary key
    private String name;
    private String major;
    
    // Default constructor
    public Student() {
    }

    // Constructor that accepts studentID, name, and major
    public Student(String studentID, String name, String major) {
        this.studentID = studentID;
        this.name = name;
        this.major = major;
    }
    
    //Getters
    /**
     * @return the studentID
     */
    public String getStudentID() {
        return studentID;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }
    /**
     * @return the course
     */
    
    
    //Setters
    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    
    
}
