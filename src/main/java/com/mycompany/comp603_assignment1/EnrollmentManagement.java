/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.*;

public class EnrollmentManagement {

    
    //connect Enrollment DB
    private final EnrollmentDAO enrollmentDAO;
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    private final Validator validator;
    
    
    
    // Constructor - Load enrollment data from file at startup
    public EnrollmentManagement() {
        this.enrollmentDAO = new EnrollmentDAO();
        this.courseDAO = new CourseDAO();
        this.studentDAO = new StudentDAO();
        this.validator = new Validator();
    }

    

    // Enroll a course to a student
    public void enrollCourseToStudent(String studentID, String courseID) {
        
       

        if (!validator.validateStudentID(studentID)) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        if (!validator.validateCourseCode(courseID)) {
            throw new IllegalArgumentException("Invalid course ID.");
        }
        if (studentDAO.searchStudent(studentID) == null) {
            throw new IllegalArgumentException("Student does not exist.");
        }
        if (courseDAO.searchCourse(courseID) == null) {
            throw new IllegalArgumentException("Course does not exist.");
        }
        if (enrollmentDAO.isEnrolled(studentID, courseID)) {
            throw new IllegalArgumentException("Student already enrolled in this course.");
        }
        enrollmentDAO.addEnrollment(studentID, courseID);
    }

    // View courses that a student has enrolled in
    public void viewStudentCourses() {
        
        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();
        
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentByStudent(studentID);
        
        if (enrollments.isEmpty()) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }

        
        
       
            System.out.println("Courses enrolled by student " + studentID + ":");
            for (Enrollment e  : enrollments) {
                System.out.println("- " + " (" + e.getCourseID() + ")");
            }
        
    }

    // Cancel a course enrollment for a student
    public void cancelEnrollment(String studentID, String courseID) {
       
        enrollmentDAO.cancelEnrollment(studentID, courseID);
        
    }

    // Get all enrollments for by student ID
    public List<Enrollment> listAllEnrollments(String studentID) {
         return enrollmentDAO.getEnrollmentByStudent(studentID);
    }
    
    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }
    //method to remove a student from all enrollment when the course is deleted
    public void removeStudentFromAllEnrollments(String studentID) {
        enrollmentDAO.deleteAllByStudent(studentID);
    }

    //method to remove a course from all students when the course is deleted
    public void removeCourseFromAllEnrollments(String courseID) {
        enrollmentDAO.deleteAllByCourse(courseID);
    }
    
    //return Mapped DB grouped by studentID
    public Map<String, List<String>> getGroupedEnrollments(){
        List<Enrollment> all = enrollmentDAO.getAllEnrollments();
        Map<String, List<String>> grouped = new HashMap<>();
        for (Enrollment e : all) {
            grouped.computeIfAbsent(e.getStudentID(), k -> new ArrayList<>()).add(e.getCourseID());
        }
        return grouped;
    }
    
    //add for EnrollmentPanel class

    public void enrollCourseToStudentFromPanel(String studentID, Course course) {
        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            enrollment = new Enrollment(studentID);
            enrollmentMap.put(studentID, enrollment);
        }
        for (Course c : enrollment.getCourseList()) {
            if (c.getCourseID().equals(course.getCourseID())) return;
        }
        enrollment.addCourseToStudent(course);
        saveEnrollmentsToFile();
    }

    public void cancelEnrollmentFromPanel(String studentID, String courseID) {
        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) return;
        enrollment.getCourseList().removeIf(c -> c.getCourseID().equals(courseID));
        saveEnrollmentsToFile();
    }

    public List<Course> getEnrolledCourses(String studentID) {
        Enrollment enrollment = enrollmentMap.get(studentID);
        return enrollment != null ? enrollment.getCourseList() : new ArrayList<>();
    }

    public static Map<String, Enrollment> getEnrollmentMap() {
        return enrollmentMap;
    }

}