/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // 학생 데이터를 파일에 저장
    public void writeStudentsToFile(String filename, List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getMajor() + "\n");
            }
            System.out.println("Student data has been written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // 학생 데이터를 파일에서 읽어오기
    public List<Student> readStudentsFromFile(String filename) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Student student = new Student();
                student.setStudentID(data[0]);
                student.setName(data[1]);
                student.setMajor(data[2]);
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return students;
    }

    // 과목 데이터를 파일에 저장
    public void writeCoursesToFile(String filename, List<Course> courses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Course course : courses) {
                writer.write(course.getCourseID() + "," + course.getCourseName() + "," + course.getCredit() + "\n");
            }
            System.out.println("Course data has been written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // 과목 데이터를 파일에서 읽어오기
    public List<Course> readCoursesFromFile(String filename) {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Course course = new Course();
                course.setCourseID(data[0]);
                course.setCourseName(data[1]);
                course.setCredit(Integer.parseInt(data[2]));
                courses.add(course);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return courses;
    }

    // 수강 데이터를 파일에 저장
    public void writeEnrollmentsToFile(String filename, List<Enrollment> enrollments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Enrollment enrollment : enrollments) {
                writer.write(enrollment.getStudentID() + "," + enrollment.getCourse().getCourseID() + "\n");
            }
            System.out.println("Enrollment data has been written to the file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // 수강 데이터를 파일에서 읽어오기
    public List<Enrollment> readEnrollmentsFromFile(String filename) {
        List<Enrollment> enrollments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Enrollment enrollment = new Enrollment(data[0]);
                Course course = new Course();
                course.setCourseID(data[1]);
                enrollment.addCourse(course); // 수업을 추가하는 방법은 상황에 맞게 처리
                enrollments.add(enrollment);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return enrollments;
    }
}
