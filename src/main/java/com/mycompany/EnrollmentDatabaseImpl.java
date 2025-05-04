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
    
    // Constructor: injects the FileHandler used for reading/writing files
    public EnrollmentDatabaseImpl(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
    
    // Writes the list of enrollments to the given file
    @Override
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        fileHandler.writeEnrollmentsToFile(enrollments, filename);
    }
    
    // Reads a list of enrollments from the given file
    @Override
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        
        
        return fileHandler.readEnrollmentsFromFile(filename);
    }

    // The following methods are not supported because this class handles only enrollment data
    
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