/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jonghapark
 */
public interface IntStudentManagement {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentID);
    Student searchStudentObject(String studentID);
    List<Student> getAllStudents();

    //add for studentmanagement and studentpanel
    StudentDAO getStudentDatabase();
    Validator getValidator();
    List<Student> getStudentList();
    Map<String, Student> getStudentMap();
}
