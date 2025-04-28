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

public class CourseDatabaseImpl extends Database {

    private FileHandler fileHandler;

    public CourseDatabaseImpl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void writeCoursesToFile(List<Course> courses, String filename) {
        fileHandler.writeCoursesToFile(courses, filename);
    }

    @Override
    public List<Course> readCoursesFromFile(String filename) {
        return fileHandler.readCoursesFromFile(filename);
    }

    @Override
    public void writeStudentsToFile(List<Student> students, String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support writeStudentsToFile.");
    }

    @Override
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support writeEnrollmentsToFile.");
    }

    @Override
    public List<Student> readStudentsFromFile(String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support readStudentsFromFile.");
    }

    @Override
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support readEnrollmentsFromFile.");
    }
}