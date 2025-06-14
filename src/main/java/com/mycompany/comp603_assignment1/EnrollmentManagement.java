/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollmentManagement implements IntEnrollmentManagement {

    
    //connect Enrollment DB
    private final EnrollmentDAO enrollmentDAO;
    private final Validator validator;
    private final StudentDAO studentDAO;
    private final CourseDAO courseDAO;
    private final List<Enrollment> enrollmentList;
    private final Map<String, Enrollment> enrollmentMap;
    
    
    
    // Constructor - Load enrollment data from file at startup
    public EnrollmentManagement() {
        this.enrollmentDAO = new EnrollmentDAO();
        this.validator = new Validator();
        this.studentDAO = new StudentDAO();
        this.courseDAO = new CourseDAO();
        this.enrollmentList = new ArrayList<>();
        this.enrollmentMap = new HashMap<>();

        List<Enrollment> loadedEnrollments = enrollmentDAO.getAllEnrollments();
        for (Enrollment e : loadedEnrollments) {
            enrollmentList.add(e);
            enrollmentMap.put(e.getStudentID(), e);
        }
    }

    

    // Enroll a course to a student
    @Override
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

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            enrollment = new Enrollment(studentID);
            enrollmentMap.put(studentID, enrollment);
        }

        Course course = courseDAO.searchCourse(courseID);
        enrollment.addCourseToStudent(course);
        if (!enrollmentList.contains(enrollment)) {
            enrollmentList.add(enrollment);
        }
    }

    // View courses that a student has enrolled in
    @Override
    public void viewStudentCourses(String studentID) {
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentByStudent(studentID);
        if (enrollments.isEmpty()) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }
        System.out.println("Courses enrolled by student " + studentID + ":");
        for (Enrollment e : enrollments) {
            System.out.println("- (" + e.getCourseID() + ")");
        }
    }

    // Cancel a course enrollment for a student
    @Override
    public void cancelEnrollment(String studentID, String courseID) {
        enrollmentDAO.cancelEnrollment(studentID, courseID);

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment != null) {
            enrollment.getCourseList().removeIf(c -> c.getCourseID().equals(courseID));
        }
    }

    // Get all enrollments for by student ID
    @Override    
    public List<Enrollment> listAllEnrollments(String studentID) {
         return enrollmentDAO.getEnrollmentByStudent(studentID);
    }
    
    // Get all enrollments
    @Override    
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.getAllEnrollments();
    }
    //method to remove a student from all enrollment when the course is deleted
    @Override    
    public void removeStudentFromAllEnrollments(String studentID) {
        enrollmentDAO.deleteAllByStudent(studentID);
    }

    //method to remove a course from all students when the course is deleted
    public void removeCourseFromAllEnrollments(String courseID) {
        enrollmentDAO.deleteAllByCourse(courseID);
    }
    
    //return Mapped DB grouped by studentID
    @Override    
    public Map<String, List<String>> getGroupedEnrollments(){
        List<Enrollment> all = enrollmentDAO.getAllEnrollments();
        Map<String, List<String>> grouped = new HashMap<>();
        for (Enrollment e : all) {
            grouped.computeIfAbsent(e.getStudentID(), k -> new ArrayList<>()).add(e.getCourseID());
        }
        return grouped;
    }
    
    //add for EnrollmentPanel class
    @Override
    public List<Course> getEnrolledCourses(String studentID) {
        Enrollment enrollment = enrollmentMap.get(studentID);
        return enrollment != null ? enrollment.getCourseList() : new ArrayList<>();
    }

    @Override
    public List<Enrollment> getEnrollmentList() {
        return enrollmentList;
    }

    @Override
    public Map<String, Enrollment> getEnrollmentMap() {
        return enrollmentMap;
    }

    @Override
    public EnrollmentDAO getEnrollmentDatabase() {
        return enrollmentDAO;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }
}