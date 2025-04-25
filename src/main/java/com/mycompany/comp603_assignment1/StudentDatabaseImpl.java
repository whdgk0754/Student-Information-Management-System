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

public class StudentDatabaseImpl extends Database {
    private List<Student> students = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully.");
    }

    @Override
    public Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }
        }
        System.out.println("Student not found.");
        return null;
    }

    @Override
    public void enrollStudent(String studentId, String courseId) {
        Student student = getStudent(studentId);
        if (student != null) {
            Course course = new Course(); // 실제 과목은 다른 곳에서 관리되어야 함
            student.addCourse(course);
            System.out.println("Student enrolled in course: " + courseId);
        }
    }

    @Override
    public boolean cancelEnrollment(String studentId, String courseId) {
        Student student = getStudent(studentId);
        if (student != null) {
            Course course = new Course(); // 실제 과목은 다른 곳에서 관리
            if (student.removeCourse(course)) {
                System.out.println("Enrollment canceled for student " + studentId);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCourse(Course course) {
        // 학생 관련 데이터는 과목 추가를 관리하지 않음
    }

    @Override
    public Course getCourse(String courseId) {
        // 학생 관련 데이터는 과목 조회를 관리하지 않음
        return null;
    }
}
