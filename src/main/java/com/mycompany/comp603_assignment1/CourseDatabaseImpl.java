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

    // Constructor: injects the FileHandler dependency
    public CourseDatabaseImpl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    // Writes a list of Course objects to the specified file
    @Override
    public void writeCoursesToFile(List<Course> courses, String filename) {
        fileHandler.writeCoursesToFile(courses, filename);
    }

    // Reads a list of Course objects from the specified file
    @Override
    public List<Course> readCoursesFromFile(String filename) {
        return fileHandler.readCoursesFromFile(filename);
    }
    
    // The following methods are not supported in this class
    @Override
    public void writeStudentsToFile(List<Student> students, String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support writeStudentsToFile.");
    }
    
    // Not supported: This class does not handle Enrollment file writing
    @Override
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support writeEnrollmentsToFile.");
    }
    
    // Not supported: This class does not handle Student file reading
    @Override
    public List<Student> readStudentsFromFile(String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support readStudentsFromFile.");
    }

    // Not supported: This class does not handle Enrollment file reading
    @Override
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        throw new UnsupportedOperationException("CourseDatabaseImpl does not support readEnrollmentsFromFile.");
    }
}