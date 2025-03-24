/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.ArrayList;
/**
 *
 * @author jonghapark
 */
public class Student extends User{
    private String studentID;
    private String name;
    private String major;
    private ArrayList<String> courses;
    //private double gpa etc..
    
    //Constructor
    Student(String studentID,String name,String major,ArrayList<String> courses){
        this.studentID = studentID;
        this.name = name;
        this.major = major;
        this.courses = courses;
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
    public ArrayList<String> getCourse() {
        return courses;
    }
    
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
    /**
     * @param courses the course to set
     */
    public void setCourse(ArrayList<String> courses) {
        this.courses = new ArrayList<>(courses);
    }
    
    
}
