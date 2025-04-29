/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gvsv0
 */
public class FileHandler {

    // Save student data to a file
    public void writeStudentsToFile(List<Student> students, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getMajor());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save course data to a file
    public void writeCoursesToFile(List<Course> courses, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Course course : courses) {
                writer.write(course.getCourseID() + "," + course.getCourseName() + "," + course.getCredit());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Save enrollment data to a file
    // Each line represents a pairing between student ID and course ID
    public void writeEnrollmentsToFile(List<Enrollment> enrollments, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Enrollment enrollment : enrollments) {
                for (Course course : enrollment.getCourseList()) {
                    writer.write(enrollment.getStudentID() + "," + course.getCourseID());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read student data from a file and return as a list of Student objects
    public List<Student> readStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3) {
                    String studentID = tokens[0];
                    String name = tokens[1];
                    String major = tokens[2];
                    students.add(new Student(studentID, name, major));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }


    // Read course data from a file and return as a list of Course objects
    public List<Course> readCoursesFromFile(String filename) {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 3) {
                    String courseID = tokens[0];
                    String courseName = tokens[1];
                    int credit = Integer.parseInt(tokens[2]);
                    courses.add(new Course(courseID, courseName, credit));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Read enrollment data from a file and return as a list of Enrollment objects
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 2) {
                    String studentID = tokens[0];
                    String courseID = tokens[1];
                    enrollments.add(new Enrollment(studentID, courseID));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enrollments;
    }
}