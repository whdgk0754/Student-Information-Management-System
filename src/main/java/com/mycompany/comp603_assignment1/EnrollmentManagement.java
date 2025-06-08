/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.*;

public class EnrollmentManagement {

    
    //connect Enrollment DB
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO(); 
    private final CourseDAO courseDAO;
    private final StudentDAO studentDAO;
    Validator validator = new Validator();
    
    
    
    // Constructor - Load enrollment data from file at startup
    public EnrollmentManagement() {
        this.enrollmentDAO = new EnrollmentDAO();
        this.courseDAO = new CourseDAO();     // check exist
        this.studentDAO = new StudentDAO();   // check exist
    }

    // Main menu for enrollment management
    public void enrollmentManagementMenu(StudentManagement studentManagement, CourseManagement courseManagement) {
        Scanner scanner = new Scanner(System.in);
        
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n=== Enrollment Management Menu ===");
            System.out.println("1. Enroll Course to Student");
            System.out.println("2. View Student Courses");
            System.out.println("3. Cancel Student Course Enrollment");
            System.out.println("4. List All Enrollments");
            System.out.println("5. Exit to Main Menu");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer

                switch (choice) {
                    case 1:
                        enrollCourseToStudent(studentManagement, courseManagement);
                        break;
                    case 2:
                        viewStudentCourses();
                        break;
                    case 3:
                        cancelEnrollment();
                        break;
                    case 4:
                        listAllEnrollments();
                        break;
                    case 5:
                        System.out.println("Exiting Enrollment Management Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
    }

    // Enroll a course to a student
    public void enrollCourseToStudent(StudentManagement studentManagement, CourseManagement courseManagement) {
        
       

        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        if (studentManagement.searchStudentObject(studentID) == null) {
            System.out.println("Student not found.");
            return;
        }

       
        System.out.println("Enter Course ID to enroll:");
        String courseID = validator.getValidCourseCode();

        
        if (courseManagement.searchCourse(courseID) == null) {
            System.out.println("Course not found.");
            return;
        }
        
        // Check if the student is already enrolled in the course      
        if(enrollmentDAO.isEnrolled(studentID, courseID)){
            System.out.println("Student already enrolled in this course.");
            return;
        }

       enrollmentDAO.addEnrollment(studentID, courseID);
        System.out.println("Course successfully enrolled for student " + studentID + ".");
        
    }

    // View courses that a student has enrolled in
    public void viewStudentCourses() {
        
        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();
        
        List<Enrollment> enrollments = enrollmentDAO.getEnrollmentByStudent(studentID);
        
        if (enrollments.isEmpty()) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }

        
        
       
            System.out.println("Courses enrolled by student " + studentID + ":");
            for (Enrollment e  : enrollments) {
                System.out.println("- " + " (" + e.getCourseID() + ")");
            }
        
    }

    // Cancel a course enrollment for a student
    public void cancelEnrollment() {
       
        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        System.out.println("Enter Course ID to cancel:");
        String courseID = validator.getValidCourseCode();
        
        enrollmentDAO.cancelEnrollment(studentID, courseID);
        
    }

    // List all enrollments for all students
    public void listAllEnrollments() {
         List<Enrollment> allEnrollments = enrollmentDAO.getAllEnrollments();

    if (allEnrollments.isEmpty()) {
        System.out.println("No enrollment records found.");
        return;
    }

    // Group by student ID
    Map<String, List<String>> grouped = new HashMap<>();
    for (Enrollment e : allEnrollments) {
        grouped.computeIfAbsent(e.getStudentID(), k -> new ArrayList<>())
               .add(e.getCourseID());
    }

    // Print by grouped enrollment
    System.out.println("All Enrollments:");
    for (Map.Entry<String, List<String>> entry : grouped.entrySet()) {
        System.out.println("----------------------------");
        System.out.println("Student ID: " + entry.getKey());
        for (String courseId : entry.getValue()) {
            System.out.println("- Course ID: " + courseId);
        }
    }
    }
    
    //method to remove a student from all enrollment when the course is deleted
    public void removeStudentFromAllEnrollments(String studentID) {
        enrollmentDAO.deleteAllByStudent(studentID);
    }

    //method to remove a course from all students when the course is deleted
    public void removeCourseFromAllEnrollments(String courseID) {
        enrollmentDAO.deleteAllByCourse(courseID);
    }
    
    //return Mapped DB grouped by studentID
    public Map<String, List<String>> getGroupedEnrollments(){
        List<Enrollment> allEnrollments = enrollmentDAO.getAllEnrollments();
        
        Map<String, List<String>> grouped = new HashMap<>();
        for(Enrollment e : allEnrollments){
            grouped.computeIfAbsent(e.getStudentID(), k-> new ArrayList<>())
                    .add(e.getCourseID());
        }
        return grouped;
    }
}

