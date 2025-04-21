/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author jonghapark
 */
public class StudentManagement {
    //declare studentList to store all student objects
    private ArrayList<Student> studentList = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();
    
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
        //add the student to the map
        studentMap.put(newStudent.getStudentID(), newStudent);
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
    Student student = studentMap.get(studentID);
        
        if(student != null){
            System.out.println("Name: " + student.getName());
            System.out.println("Major: " + student.getMajor());
            
            System.out.println("\nWhich data would you like to update?");
            System.out.println("1. Student name");
            System.out.println("2. Major");
            System.out.println("3. exit");
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    System.out.print("Enter new name: ");
                    student.setName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new major: ");
                    student.setMajor(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Error: you entered invalid input. try again.");
                    break;
                 
            }
            
        }else
            System.out.println("Student ID is not valid: " + studentID);
            
            
     
    }
    
    public boolean deleteStudent(String studentID){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure about removing " + studentID + "?");
        String confirm = scanner.next();
        
        if(!confirm.equalsIgnoreCase("y")){
            System.out.println("Deletion cancelled.");
            return false;
        }
        
        boolean foundList = false;
        Iterator<Student> iterator = studentList.iterator();
        
        
        while (iterator.hasNext()){
            Student s = iterator.next();
            if(s.getStudentID().equals(studentID)){
                iterator.remove();
                foundList = true;
                break;
            }
        }
        
        if(foundList){
            studentMap.remove(studentID);
            System.out.println("Student successfully deleted.");
            return true;
        }else{
            System.out.println("Student Not Found");
            return false;
        }
        
    
    }
    public void searchStudent(String studentID){
        
        Student student = studentMap.get(studentID);
        if(student != null){
            System.out.println("Name: " + student.getName());
        System.out.println("studentID: " + student.getStudentID());
        System.out.println("Major: " + student.getMajor());
        }else{
            System.out.println("Student Not Found with ID: " + studentID);
            
        }
        
        
        
    
    }
    
        


}
    
