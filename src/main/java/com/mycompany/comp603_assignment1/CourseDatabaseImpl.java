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

public class CourseDatabaseImpl extends Database {
    private List<Course> courses = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added successfully.");
    }

    @Override
    public Course getCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseID().equals(courseId)) {
                return course;
            }
        }
        System.out.println("Course not found.");
        return null;
    }

    @Override
    public void enrollStudent(String studentId, String courseId) {
        // 과목 관련 데이터는 수강 신청을 관리하지 않음
    }

    @Override
    public boolean cancelEnrollment(String studentId, String courseId) {
        // 과목 관련 데이터는 수강 취소를 관리하지 않음
        return false;
    }

    @Override
    public void addStudent(Student student) {
        // 과목 관련 데이터는 학생 추가를 관리하지 않음
    }

    @Override
    public Student getStudent(String studentId) {
        // 과목 관련 데이터는 학생 조회를 관리하지 않음
        return null;
    }
}
