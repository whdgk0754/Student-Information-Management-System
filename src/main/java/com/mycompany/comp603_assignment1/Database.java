/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
// Abstract Database class
public abstract class Database {

    // 학생 추가
    public abstract void addStudent(Student student);

    // 학생 조회 (ID로)
    public abstract Student getStudent(String studentId);

    // 과목 추가
    public abstract void addCourse(Course course);

    // 과목 조회 (ID로)
    public abstract Course getCourse(String courseId);

    // 수강 신청 (학생 ID와 과목 ID로 연결)
    public abstract void enrollStudent(String studentId, String courseId);

    // 수강 취소 (학생 ID와 과목 ID로)
    public abstract boolean cancelEnrollment(String studentId, String courseId);
    
    // 데이터베이스 연결 (예시: 파일 연결, DB 연결 등)
    public void connectToDatabase() {
        System.out.println("Connecting to the database...");
    }
}
