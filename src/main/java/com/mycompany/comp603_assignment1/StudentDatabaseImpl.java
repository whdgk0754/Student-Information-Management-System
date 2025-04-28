/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.List;


public class StudentDatabaseImpl extends Database {

    private FileHandler fileHandler;

    public StudentDatabaseImpl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void writeStudentsToFile(List<Student> students, String filename) {
        fileHandler.writeStudentsToFile(students, filename);
    }

    @Override
    public List<Student> readStudentsFromFile(String filename) {
        return fileHandler.readStudentsFromFile(filename);
    }

    // ❌ 필요 없는 메소드들: UnsupportedOperationException 처리

    @Override
    public void writeCoursesToFile(List<Course> courses, String filename) {
        throw new UnsupportedOperationException("StudentDatabaseImpl does not support writeCoursesToFile.");
    }

    @Override
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        throw new UnsupportedOperationException("StudentDatabaseImpl does not support writeEnrollmentsToFile.");
    }

    @Override
    public List<Course> readCoursesFromFile(String filename) {
        throw new UnsupportedOperationException("StudentDatabaseImpl does not support readCoursesFromFile.");
    }

    @Override
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        throw new UnsupportedOperationException("StudentDatabaseImpl does not support readEnrollmentsFromFile.");
    }
}