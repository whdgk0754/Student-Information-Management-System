/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author jonghapark
 */
public class StudentManagement {
    //declare studentList to store all student objects
    private ArrayList<Student> studentList = new ArrayList<>();
    
    public void addStudent(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> courses = new ArrayList<>();
        //create a new student instance
        Student newStudent = new Student();
        
        //using setters
        System.out.println("Enter student name : ");
        newStudent.setName(scanner.next());
        
        System.out.println("Enter student major : ");
        newStudent.setMajor(scanner.next());
        
        System.out.println("Enter student ID : ");
        newStudent.setStudentID(scanner.next());
        
        System.out.println("Enter student courses : ");
        while(true){
            String course = scanner.next();
            if(course.equalsIgnoreCase("done"))
                break;
            courses.add(course);
        }
        
        newStudent.setCourse(courses);
        //add the student to the list
        studentList.add(newStudent);
        //require fuction that links to database 
        System.out.println("Student added successfully.");
        
    }

    public void displayStudent(){
        //function that load from DB
        if(studentList.isEmpty()){
            System.out.println("No student found");
            return; 
        }
        for(Student student: studentList){
            System.out.println(student);
        }
    }
    public void updateStudent(String studentID){
    //load DB and use switch to update
    Scanner scanner = new Scanner(System.in);
    //check studentList is empty
    System.out.println("Current Students: " + studentList.size());
    for(Student student: studentList){
        
        if(student.getStudentID().equals(studentID)){
            System.out.println("Which data would you like to update?");
            System.out.println("1. Student name");
            System.out.println("2. Major");
            System.out.println("3. courses");
            System.out.println("4. exit");
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    System.out.print("Enter new name: ");
                    student.setName(scanner.next());
                    break;
                case 2:
                    System.out.print("Enter new major: ");
                    student.setMajor(scanner.next());
                    break;
                case 3:
                    System.out.print("Enter new course: ");
                    //student.setCourse(scanner.next());
                    break;
                case 4:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Error: you entered invalid input. try again.");
                    break;
                 
            }
        }else
            System.out.println("Student ID is not valid. try again.");
            
            
     }
    }
    
    public void deleteStudent(){}
    public void searchStudent(){}
    



}
    
