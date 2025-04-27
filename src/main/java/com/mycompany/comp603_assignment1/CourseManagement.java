/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author jonghapark
 */
public class CourseManagement {
    private List<Course> courseList = new ArrayList<>();
    private HashMap<String, Course> courseMap = new HashMap<>();
    
    
    public void courseManagementMenu(){
        Scanner scanner = new Scanner(System.in);
        Validator validator = new Validator();
        int choice = 0;
        
        
        while (choice != 6) {
            System.out.println("\n=== Course Management Menu ===");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Search Course");
            System.out.println("5. List All Courses");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer
                
                
                switch(choice){
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        System.out.println("Enter Course ID to update: ");
                        String updateID = validator.getValidCourseCode();
                        updateCourse(updateID);
                        break;
                    case 3:
                        System.out.println("Enter Course ID to delete: ");
                        String deleteID = validator.getValidCourseCode();
                        System.out.println("Are you sure you want to delete " + deleteID + "? (y/n): ");
                        String confirm = scanner.nextLine();
                        deleteCourse(deleteID, confirm);
                        break;
                    case 4:  
                        System.out.println("Enter CourseID to search: ");
                        String searchID = validator.getValidCourseCode();
                        searchCourse(searchID);
                        break;
                        
                    case 5:
                        listAllCourses();
                        break;
                    case 6:    
                        System.out.println("\"Exiting Course Management Menu...");
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
    
    
    public void addCourse(){
        Validator validator = new Validator();
        Scanner scanner = new Scanner(System.in);
        Course newCourse = new Course();
        
        
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        while(!validator.validateCourseName(courseName)){
            System.out.println("Invalid course name. Please enter again.:");
            courseName = scanner.nextLine();
        }
        newCourse.setCourseName(courseName);
        
        System.out.println("Enter course ID:");
        newCourse.setCourseID(validator.getValidCourseCode()); //ex. MATH101
        
        System.out.println("Enter course credit:");
        String credit = scanner.next();
        while(!validator.isValidInteger(credit)){
            System.out.println("Invalid input. Please enter a valid number");
            credit =scanner.next();
        }
        newCourse.setCredit(Integer.parseInt(credit));
        
        
        
        courseList.add(newCourse);
        courseMap.put(newCourse.getCourseID(), newCourse); //?
        System.out.println("Course has been added successfully");
        
        
        
    }
    
    public void updateCourse(String courseID){
        Scanner scanner = new Scanner(System.in);
        Validator validator = new Validator();
        Course course = courseMap.get(courseID);
        
        if(course!= null){
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Credit: " + course.getCredit());
            
            System.out.println("\nWhich data would you like to update?");
            System.out.println("1. course name");
            System.out.println("2. credit");
            System.out.println("3. exit");
            
            int input = scanner.nextInt();
            switch(input){
                case 1:
                    System.out.print("Enter new course name: ");
                    String courseName = scanner.nextLine();
                    while(!validator.validateCourseName(courseName)){
                        System.out.println("Invalid course name. Please enter again.:");
                        courseName = scanner.nextLine();
                    }
                    course.setCourseName(courseName);
                    break;
                case 2:
                    System.out.print("Enter new credit: ");
                    String credit = scanner.next();
                    while(!validator.isValidInteger(credit)){
                        System.out.println("Invalid input. Please enter a valid number");
                        credit =scanner.next();
                    }
                     course.setCredit(Integer.parseInt(credit));
                    break;
                case 3:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Error: you entered invalid input. try again.");
                    break;
                 
            }
            
            
        }else
            System.out.println("Course ID is not valid: " + courseID);
    }
    
    
    public boolean deleteCourse(String courseID, String confirm){

        if(!confirm.equalsIgnoreCase("y")){
            System.out.println("Deletion cancelled.");
            return false;
        }
        
        boolean foundList = false;
        Iterator<Course> iterator = courseList.iterator();
        
        
        while (iterator.hasNext()){
            Course c = iterator.next();
            if(c.getCourseID().equals(courseID)){
                iterator.remove();
                foundList = true;
                break;
            }
        }
        
        if(foundList){
            courseMap.remove(courseID);
            System.out.println("Course successfully deleted.");
            return true;
        }else{
            System.out.println("Course Not Found");
            return false;
        }
        
    }
    
    public Course searchCourse(String courseID){
        Course course = courseMap.get(courseID);
        if(course != null){
            return course;
        }else{
            System.out.println("Course Not Found with ID: " + courseID);
            return null;
    }
    
    }
    
    public void listAllCourses(){
        if(courseList.isEmpty()){
            System.out.println("Course List is empty.");
            return;
        }
        
        System.out.println("All Available Courses:");
        for(Course course : courseList){
            System.out.println("----------------------------");
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Credit: " + course.getCredit());
        }
    }
}
