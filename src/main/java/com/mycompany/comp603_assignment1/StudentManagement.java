/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.Scanner;
import java.util.List;
/**
 *
 * @author jonghapark
 */
public class StudentManagement {
    private final StudentDAO studentDAO;
    private final Validator validator;
    
    

    // constructor - loads database
    public StudentManagement() {
        this.studentDAO = new StudentDAO();
        this.validator = new Validator();
        
    }
    
    
    
    //add new student
    public void addStudent(Student student){
        
        
       if (!validator.validateStudentID(student.getStudentID())) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        if (!validator.validateName(student.getName())) {
            throw new IllegalArgumentException("Invalid student name.");
        }
        if (!validator.validateMajor(student.getMajor())) {
            throw new IllegalArgumentException("Invalid major.");
        }
        if (studentDAO.searchStudent(student.getStudentID()) != null) {
            throw new IllegalArgumentException("Student ID already exists.");
        }
        studentDAO.addStudent(student);
        
    }
    
    //list all students
    public List<Student> listAllStudents(){
        return studentDAO.getAllStudent();
    }
    
    //update students
    public void updateStudent(Student student){
    
    if (studentDAO.searchStudent(student.getStudentID()) == null) {
            throw new IllegalArgumentException("Student ID not found.");
        }
        studentDAO.updateStudent(student);
    }
    
    //delete student
    public void deleteStudent(String studentID){
        
        Student student = studentDAO.searchStudent(studentID);
        if (student == null) {
            throw new IllegalArgumentException("Student ID not found.");
        }
        studentDAO.deleteStudent(student);
    }
    
    //search student from database
    public Student searchStudentObject(String id) {
        return studentDAO.searchStudent(id); 
    }
    
    //add for StudentPanel class
    public List<Student> getStudentList() {
        return studentList;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public StudentDatabaseImpl getStudentDatabase() {
        return studentDatabase;
    }
    
        

}


    

    
