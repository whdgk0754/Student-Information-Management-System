/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
import java.util.ArrayList;



/**
 *
 * @author jonghapark
 */
public class Enrollment {
    private String studentID;
    private List<Course> courseList = new ArrayList<>();
    
    
    Enrollment(String studentID){
        this.studentID = studentID;
        this.courseList = new ArrayList<>();
          
    }
    
    //return this.studentID
    public String getStudentID(){
        return studentID;
    }
    //return this student courseList
    public List<Course> getCourseList(){
        return courseList;
    }
    
    //add courses to this student
    public void addCourseToStudent(Course course){
        if(!courseList.contains(course)){
            courseList.add(course);
            System.out.println("Course " + course.getCourseID() + "added to student " + studentID);
        }else{
            System.out.println("Student " + studentID + "already enrolled in course " + course.getCourseID());
        }
    }
    
}
