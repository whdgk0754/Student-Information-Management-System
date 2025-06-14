/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author jonghapark
 */
public class CourseManagement {
    private final CourseDAO courseDAO;
    private final Validator validator;
    
    
    // Constructor: Load existing courses from file into list and map
    public CourseManagement() {
        this.courseDAO = new CourseDAO();
        this.validator = new Validator();
       
    }
    
    
    
    // Adds a new course to the system after validation    
    public void addCourse(Course course){
        if (!validator.validateCourseCode(course.getCourseID())) {
            throw new IllegalArgumentException("Invalid course code.");
        }
        if (!validator.validateCourseName(course.getCourseName())) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        if (course.getCredit() < 0) {
            throw new IllegalArgumentException("Credit must be a positive number.");
        }
        if (courseDAO.searchCourse(course.getCourseID()) != null) {
            throw new IllegalArgumentException("Course ID already exists.");
        }
        courseDAO.addCourse(course);
        
    }
    
    // Updates course name or credit by course    
    public void updateCourse(Course course){
        if (courseDAO.searchCourse(course.getCourseID()) == null) {
            throw new IllegalArgumentException("Course ID not found.");
        }
        courseDAO.updateCourse(course);
    }
    
    // Deletes a course from the system by ID and confirmation
    public void deleteCourse(String courseID){
        
       Course course = courseDAO.searchCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("Course ID not found.");
        }
        courseDAO.deleteCourse(course);
    }
    
    // Searches for a course and prints details if found
    public Course searchCourse(String courseID) {
        return courseDAO.searchCourse(courseID);
    }
    
    // Lists all courses stored in the system
    public List<Course> listAllCourses(){
        return courseDAO.getAllCourses();
    }
}
