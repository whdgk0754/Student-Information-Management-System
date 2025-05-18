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
// Abstract Database class (responsible for file I/O operations)
public abstract class Database {

    // Save student data to a file
    public abstract void writeStudentsToFile(List<Student> students, String filename);

    // Save course data to a file
    public abstract void writeCoursesToFile(List<Course> courses, String filename);

    // Save enrollment data to a file
    public abstract void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename);

    // Read student data from a file
    public abstract List<Student> readStudentsFromFile(String filename);

    // Read course data from a file
    public abstract List<Course> readCoursesFromFile(String filename);

    // Read enrollment data from a file
    public abstract List<Enrollment> readEnrollmentsFromFile(String filename);
}
