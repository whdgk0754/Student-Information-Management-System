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
    Validator validator = new Validator();
    private CourseDatabaseImpl courseDatabase = new CourseDatabaseImpl(new FileHandler());
    
    // Constructor: Load existing courses from file into list and map
    public CourseManagement() {
        List<Course> loadedCourses = courseDatabase.readCoursesFromFile("courses.txt");
        for (Course course : loadedCourses) {
            courseList.add(course);
            courseMap.put(course.getCourseID(), course);
        }
    }
    
    // Displays and handles the course management menu
    public void courseManagementMenu(){
        Scanner scanner = new Scanner(System.in);
        
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
                    scanner.next();// discard invalid input
                    }
            
            
        }
    }
    
    // Adds a new course to the system after validation    
    public void addCourse(){
        
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
        String courseID = validator.getValidCourseCode(); 
        
        // Prevent duplicate course ID
        if (courseMap.containsKey(courseID)) {
            System.out.println("Course ID already exists. Cannot add duplicate course.");
            return;
        }
        
        newCourse.setCourseID(courseID);
        
        System.out.println("Enter course credit:");
        String credit = scanner.next();
        while(!validator.isValidInteger(credit)){
            System.out.println("Invalid input. Please enter a valid number");
            credit =scanner.next();
        }
        newCourse.setCredit(Integer.parseInt(credit));
        
        
        // Save to collection and file
        courseList.add(newCourse);
        courseMap.put(newCourse.getCourseID(), newCourse); //?
        
        courseDatabase.writeCoursesToFile(courseList, "courses.txt");
        
        System.out.println("Course has been added successfully");
        
        
        
    }
    
    // Updates course name or credit by course ID    
    public void updateCourse(String courseID){
        Scanner scanner = new Scanner(System.in);
        
        Course course = courseMap.get(courseID);
        
        if(course!= null){
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Credit: " + course.getCredit());
            
            System.out.println("\nWhich data would you like to update?");
            System.out.println("1. Course name");
            System.out.println("2. Credit");
            System.out.println("3. Exit");
            
            int input = scanner.nextInt();// consume newline
            scanner.nextLine();
            
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

            courseDatabase.writeCoursesToFile(courseList, "courses.txt");

            System.out.println("Course updated successfully.");
            
            
        }else
            System.out.println("Course ID is not valid: " + courseID);
    }
    
    // Deletes a course from the system by ID and confirmation
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
            courseDatabase.writeCoursesToFile(courseList, "courses.txt");
            EnrollmentManagement.removeCourseFromAllEnrollments(courseID);
            System.out.println("Course successfully deleted.");
            return true;
        }else{
            System.out.println("Course Not Found");
            return false;
        }
        
    }
    
    // Searches for a course and prints details if found
    public Course searchCourse(String courseID) {
        Course course = courseMap.get(courseID);
        if (course != null) {
            System.out.println("Course ID: " + course.getCourseID());
            System.out.println("Course Name: " + course.getCourseName());
            System.out.println("Credit: " + course.getCredit());
            return course;
        } else {
            System.out.println("Course Not Found with ID: " + courseID);
            return null;
        }
    }
    
    // Lists all courses stored in the system
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
