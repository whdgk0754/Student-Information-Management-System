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

public class EnrollmentDatabaseImpl extends Database {

    private FileHandler fileHandler;

    public EnrollmentDatabaseImpl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        fileHandler.writeEnrollmentsToFile(enrollments, filename);
    }

    @Override
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        return fileHandler.readEnrollmentsFromFile(filename);
    }

    @Override
    public void writeStudentsToFile(List<Student> students, String filename) {
        throw new UnsupportedOperationException("EnrollmentDatabaseImpl does not support writeStudentsToFile.");
    }

    @Override
    public void writeCoursesToFile(List<Course> courses, String filename) {
        throw new UnsupportedOperationException("EnrollmentDatabaseImpl does not support writeCoursesToFile.");
    }

    @Override
    public List<Student> readStudentsFromFile(String filename) {
        throw new UnsupportedOperationException("EnrollmentDatabaseImpl does not support readStudentsFromFile.");
    }

    @Override
    public List<Course> readCoursesFromFile(String filename) {
        throw new UnsupportedOperationException("EnrollmentDatabaseImpl does not support readCoursesFromFile.");
    }
}