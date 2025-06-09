/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

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
    
    
    //Menu
    public void studentManagementMenu(CourseManagement courseManagement){
        Scanner scanner = new Scanner(System.in);
       
        int choice = 0;
        
        
        while (choice != 6) {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            //System.out.println("4. Search Student");
            System.out.println("4. List All Student");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer
                
                
                switch(choice){
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:  
                        listAllStudents();
                        break;
                    case 5:
                        System.out.println("Exiting Student Management Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        
                } 
                
            }else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                    }
            
            
        }
    }
    //add new student
    public void addStudent(){
        
        
       System.out.println("Enter Student ID:");
        String id = validator.getValidStudentID();

        if (studentDAO.exists(id)) {
            System.out.println("Student ID already exists.");
            return;
        }
        System.out.println("Enter Student Name:");
        String name = validator.getValidName();

        System.out.println("Enter Student Major:");
        String major = validator.getValidMajor();

        Student student = new Student(id, name, major);
        studentDAO.addStudent(student);
        System.out.println("Student added successfully.");
       
        
    }
    
    //list all students
    public void listAllStudents(){
        
        List<Student> students = studentDAO.getAllStudent();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("All Students:");
        for (Student s : students) {
            System.out.println("ID: " + s.getStudentID());
            System.out.println("Name: " + s.getName());
            System.out.println("Major: " + s.getMajor());
            System.out.println("------------------------");
        }
    }
    
    //update students
    public void updateStudent(){
    
    System.out.println("Enter Student ID to update:");
        String id = validator.getValidStudentID();

        Student student = studentDAO.searchStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new name:");
        String name = validator.getValidName();

        System.out.println("Enter new major:");
        String major = validator.getValidMajor();

        student.setName(name);
        student.setMajor(major);

        studentDAO.updateStudent(student);
        System.out.println("Student updated successfully.");
    }
    
    //delete student
    public void deleteStudent(){
        
        System.out.println("Enter Student ID to delete:");
        String id = validator.getValidStudentID();

        Student student = studentDAO.searchStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        studentDAO.deleteStudent(student);
        System.out.println("Student deleted successfully.");
    }
    
    //search student from database
    public Student searchStudentObject(String id) {
        return studentDAO.searchStudent(id); // for other classes like EnrollmentManagement
    }
    
        

}


    

    
