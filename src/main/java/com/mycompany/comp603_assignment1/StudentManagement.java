/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author jonghapark
 */
public class StudentManagement implements IntStudentManagement{
    private final StudentDAO studentDAO;
    private final Validator validator;
    
    //add for studentpanel
    private final List<Student> studentList;
    private final Map<String, Student> studentMap;
    
    

    // constructor - loads database
    public StudentManagement() {
        this.studentDAO = new StudentDAO();
        this.validator = new Validator();
        
        //add for studentpanel
        this.studentList = new ArrayList<>();
        this.studentMap = new HashMap<>();

        List<Student> loadedStudents = studentDAO.getAllStudent();
        for (Student s : loadedStudents) {
            studentList.add(s);
            studentMap.put(s.getStudentID(), s);
        }
        
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
        
        //add for studentpanel
        studentList.add(student);       
        studentMap.put(student.getStudentID(), student);
        
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
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        enrollmentDAO.deleteAllByStudent(studentID);
        studentDAO.deleteStudent(student);
        
        //add for studentpanel
        studentList.remove(student);      
        studentMap.remove(studentID); 
    }
    
    //search student from database
    public Student searchStudentObject(String studentID) {
        return studentDAO.searchStudent(studentID); 
    }
    
    //add for StudentPanel class
    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudent();
    }

    @Override
    public List<Student> getStudentList() {
        return studentDAO.getAllStudent();
    }

    @Override
    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    @Override
    public StudentDAO getStudentDatabase() {
        return studentDAO;
    }

    @Override
    public Validator getValidator() {
        return validator;
    } 

}


    

    
