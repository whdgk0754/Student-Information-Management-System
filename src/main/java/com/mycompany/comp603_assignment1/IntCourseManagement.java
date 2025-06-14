/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
/**
 *
 * @author jonghapark
 */
public interface IntCourseManagement {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(String id);
    Course searchCourse(String id);
    List<Course> getAllCourses();
}