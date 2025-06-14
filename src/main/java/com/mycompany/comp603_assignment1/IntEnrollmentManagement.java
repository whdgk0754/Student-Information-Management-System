/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
import java.util.Map;
/**
 *
 * @author jonghapark
 */
public interface IntEnrollmentManagement {
    void enrollCourseToStudent(String studentID, String courseID);
    void cancelEnrollment(String studentID, String courseID);
    List<Enrollment> listAllEnrollments(String studentID);
    List<Enrollment> getAllEnrollments();
    Map<String, List<String>> getGroupedEnrollments();
    void removeStudentFromAllEnrollments(String studentID);
    void removeCourseFromAllEnrollments(String courseID);
    

    List<Course> getEnrolledCourses(String studentID);

    EnrollmentDAO getEnrollmentDatabase();
    Validator getValidator();
    List<Enrollment> getEnrollmentList();
    Map<String, Enrollment> getEnrollmentMap();
    
    void viewStudentCourses(String studentID);
    
    
}
