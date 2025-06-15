/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author jonghapark
 */
public class CourseManagement implements IntCourseManagement{
    private final CourseDAO courseDAO;
    private final Validator validator;
    
    //add for coursepanel
    private final List<Course> courseList;
    private final Map<String, Course> courseMap;
    
    
    // Constructor: Load existing courses from file into list and map
    public CourseManagement() {
        this.courseDAO = new CourseDAO();
        this.validator = new Validator();
        
       //add for coursepanel
        this.courseList = new ArrayList<>();
        this.courseMap = new HashMap<>();
        
        List<Course> loadedCourses = courseDAO.getAllCourses();
        for (Course c : loadedCourses) {
            courseList.add(c);
            courseMap.put(c.getCourseID(), c);
        }
       
    }
    
    
    
    // Adds a new course to the system after validation   
    @Override
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
        
        //add for coursepanel
        courseList.add(course);
        courseMap.put(course.getCourseID(), course);        
    }
    
    // Updates course name or credit by course    
    @Override
    public void updateCourse(Course course){
        if (courseDAO.searchCourse(course.getCourseID()) == null) {
            throw new IllegalArgumentException("Course ID not found.");
        }
        courseDAO.updateCourse(course);
    }
    
    // Deletes a course from the system by ID and confirmation
    @Override
    public void deleteCourse(String courseID){
        
       Course course = courseDAO.searchCourse(courseID);
        if (course == null) {
            throw new IllegalArgumentException("Course ID not found.");
        }
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        enrollmentDAO.deleteAllByCourse(courseID);
        courseDAO.deleteCourse(course);
        
        //add for coursepanel
        courseList.remove(course);
        courseMap.remove(courseID);
    }
    
    // Searches for a course and prints details if found
    @Override
    public Course searchCourse(String courseID) {
        return courseDAO.searchCourse(courseID);
    }
    
    // add for CoursePanel class
    
    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }
    
    @Override
    public List<Course> getCourseList() {
        return courseDAO.getAllCourses();
    }

    @Override
    public Map<String, Course> getCourseMap() {
        return courseMap;
    }

    @Override
    public CourseDAO getCourseDatabase() {
        return courseDAO;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

}
