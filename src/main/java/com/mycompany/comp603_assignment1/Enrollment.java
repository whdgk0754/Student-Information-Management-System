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
public class Enrollment {
    private String studentID;
    private List<Course> courseList;
    private HashMap<String, Course> courseMap;
    
    Enrollment(String studentID){
        this.studentID = studentID;
        this.courseList = new ArrayList<>();
        this.courseMap = new HashMap<>();   
    }
    
    public void addCourse(){
        Scanner scanner = new Scanner(System.in);
        Course newCourse = new Course();
        
        
        System.out.println("Enter course name:");
        newCourse.setCourseName(scanner.next());
        System.out.println("Enter course ID:");
        newCourse.setCourseID(scanner.next());
        System.out.println("Enter course credit:");
        newCourse.setCredit(scanner.nextInt());
        
        courseList.add(newCourse);
        courseMap.put(newCourse.getCourseID(), newCourse); //?
        System.out.println("Course has been added successfully");
        
        
        
    }
    
    public void updateCourse(String courseID){
        Scanner scanner = new Scanner(System.in);
        
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
                    course.setCourseName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new credit: ");
                    course.setCredit(scanner.nextInt());
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
    public boolean deleteCourse(String courseID){
         Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure about removing " + courseID + "?");
        String confirm = scanner.next();
        
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
    
    public void searchCourse(String courseID){
        Course course = courseMap.get(courseID);
        if(course != null){
            System.out.println("Course Name: " + course.getCourseName());
        System.out.println("CourseID: " + course.getCourseID());
        System.out.println("Credit: " + course.getCredit());
        }else{
            System.out.println("Course Not Found with ID: " + courseID);
    }
    
    }
    
    
}
