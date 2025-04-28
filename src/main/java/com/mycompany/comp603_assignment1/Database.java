/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

import java.util.List;

/**
 *
 * @author gvsv0
 */
// Abstract Database class (파일 입출력 담당)
public abstract class Database {

    // 학생 데이터를 파일에 저장
    public abstract void writeStudentsToFile(List<Student> students, String filename);

    // 과목 데이터를 파일에 저장
    public abstract void writeCoursesToFile(List<Course> courses, String filename);

    // 수강 데이터를 파일에 저장
    public abstract void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename);

    // 학생 데이터를 파일에서 읽기
    public abstract List<Student> readStudentsFromFile(String filename);

    // 과목 데이터를 파일에서 읽기
    public abstract List<Course> readCoursesFromFile(String filename);

    // 수강 데이터를 파일에서 읽기
    public abstract List<Enrollment> readEnrollmentsFromFile(String filename);
}
