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
    //declare studentList to store all student objects
    private ArrayList<Student> studentList = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();
    //Each student has their own courses
    private Map<String, Enrollment> enrollmentMap = new HashMap<>();
    
    
    
    
    public void studentManagementMenu(CourseManagement courseManagement){
        Scanner scanner = new Scanner(System.in);
        Validator validator = new Validator();
        int choice = 0;
        
        
        while (choice != 6) {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. List All Student");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer
                
                
                switch(choice){
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        System.out.println("Enter student ID to update: ");
                        String updateID = validator.getValidStudentID();
                        updateStudent(updateID);
                        break;
                    case 3:
                        System.out.println("Enter student ID to delete: ");
                        String deleteID = validator.getValidStudentID();
                        System.out.println("Are you sure you want to delete " + deleteID + "? (y/n): ");
                        String confirm = scanner.nextLine();
                        deleteStudent(deleteID, confirm);
                        break;
                    case 4:  
                        System.out.println("Enter student ID to search: ");
                        String searchID = validator.getValidStudentID();
                        searchStudent(searchID);
                        break;
                        
                    case 5:
                        listAllStudents();
                        break;
                    case 6:
                        System.out.println("Enter student ID to enroll: ");
                        String enrollID = validator.getValidStudentID();
                        enrollCourseToStudent(enrollID, courseManagement);
                    case 7:
                        System.out.println("Enter student ID to list student's courses: ");
                        String studentID = validator.getValidStudentID();
                        viewStudentCourses(studentID);
                        break;
                        
                    case 8:    
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
    public void addStudent(){
        Scanner scanner = new Scanner(System.in);
        Validator validator = new Validator();
        ArrayList<String> courses = new ArrayList<>();
        //create a new student instance
        Student newStudent = new Student();
        String check;
        //using setters
        System.out.println("Enter student name : ");
        newStudent.setName(validator.getValidName());
        
        System.out.println("Enter student major : ");
        newStudent.setMajor(validator.getValidMajor());
        
        System.out.println("Enter student ID : ");
        newStudent.setStudentID(validator.getValidStudentID());
        
        
        
       
        //add the student to the list
        studentList.add(newStudent);
        //add the student to the map
        studentMap.put(newStudent.getStudentID(), newStudent);
        //require fuction that links to database 
        System.out.println("Student added successfully.");
        
    }

    public void listAllStudents(){
        //function that load from DB
        if(studentList.isEmpty()){
            System.out.println("No student found");
            return; 
        }
        System.out.println("All Students:");
        for(Student student : studentList){
            System.out.println("----------------------------");
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Major: " + student.getMajor());
        }
    }
    public void updateStudent(String studentID){
    Validator validator = new Validator();
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
                    student.setName(validator.getValidName());
                    break;
                case 2:
                    System.out.print("Enter new major: ");
                    student.setMajor(validator.getValidMajor());
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
    
    public boolean deleteStudent(String studentID, String confirm){
        
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
    
        
public void viewStudentCourses(String studentID){
    Enrollment enrollment = enrollmentMap.get(studentID);
    if (enrollment == null) {
        System.out.println("Student not found or no enrollment record.");
        return;
    }

    List<Course> courses = enrollment.getCourseList();
    if (courses.isEmpty()) {
        System.out.println("No courses enrolled for this student.");
    } else {
        System.out.println("Courses enrolled by student " + studentID + ":");
        for (Course course : courses) {
            System.out.println("- " + course.getCourseName() + " (" + course.getCourseID() + "), Credit: " + course.getCredit());
        }
    }
}

public void enrollCourseToStudent(String studentID, CourseManagement courseManagement) {
    Validator validator = new Validator();
    // check student exists
    Student student = studentMap.get(studentID);
    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    // check student has enrolled
    Enrollment enrollment = enrollmentMap.get(studentID);
    if (enrollment == null) {
        enrollment = new Enrollment(studentID);
        enrollmentMap.put(studentID, enrollment);
    }

    System.out.println("Enter Course ID to enroll:");
    String courseID = validator.getValidCourseCode();

    Course course = courseManagement.searchCourse(courseID);
    if (course == null) {
        return;
    }

    enrollment.addCourseToStudent(course);
    System.out.println("Course successfully enrolled for student " + studentID + ".");
}

}
    
