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
import java.util.List;

public class EnrollmentDatabaseImpl extends Database {
    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public void enrollStudent(String studentId, String courseId) {
        Enrollment enrollment = new Enrollment(studentId, courseId);
        enrollments.add(enrollment);
        System.out.println("Enrollment successful for student " + studentId + " in course " + courseId);
    }

    @Override
    public boolean cancelEnrollment(String studentId, String courseId) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentID().equals(studentId) && enrollment.getCourseID().equals(courseId)) {
                enrollments.remove(enrollment);
                System.out.println("Enrollment canceled for student " + studentId + " in course " + courseId);
                return true;
            }
        }
        System.out.println("Enrollment not found.");
        return false;
    }

    @Override
    public void addCourse(Course course) {
        // 수강 관련 데이터는 과목 추가를 관리하지 않음
    }

    @Override
    public Course getCourse(String courseId) {
        // 수강 관련 데이터는 과목 조회를 관리하지 않음
        return null;
    }

    @Override
    public void addStudent(Student student) {
        // 수강 관련 데이터는 학생 추가를 관리하지 않음
    }

    @Override
    public Student getStudent(String studentId) {
        // 수강 관련 데이터는 학생 조회를 관리하지 않음
        return null;
    }
}
