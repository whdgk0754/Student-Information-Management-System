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
    
    // Default constructor
    public Enrollment() {
    }

    // Create a Course object using only courseID; name and credit are set to defaults
    public Enrollment(String studentID, String courseID) {
        this.studentID = studentID;
        this.courseList = new ArrayList<>();
        Course course = new Course(courseID, "", 0); 
        addCourseToStudent(course);
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
            courseList.add(course);
            
    
}
}
