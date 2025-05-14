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

    private static Map<String, Enrollment> enrollmentMap = new HashMap<>();
    
    private static EnrollmentDatabaseImpl enrollmentDatabase = new EnrollmentDatabaseImpl(new FileHandler());

    Validator validator = new Validator();
    
    // Constructor - Load enrollment data from file at startup
    public EnrollmentManagement() {
        List<Enrollment> loadedEnrollments = enrollmentDatabase.readEnrollmentsFromFile("enrollments.txt");
        for (Enrollment enrollment : loadedEnrollments) {
            enrollmentMap.put(enrollment.getStudentID(), enrollment);
        }
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

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            enrollment = new Enrollment(studentID);
            enrollmentMap.put(studentID, enrollment);
        }

        System.out.println("Enter Course ID to enroll:");
        String courseID = validator.getValidCourseCode();

        Course course = courseManagement.searchCourse(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        // Check if the student is already enrolled in the course      
        for (Course c : enrollment.getCourseList()) {
            if (c.getCourseID().equals(courseID)) {
                System.out.println("Student already enrolled in this course.");
                return;
            }
        }

        enrollment.addCourseToStudent(course);

        // Save the updated enrollments to file
        enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");

        System.out.println("Course successfully enrolled for student " + studentID + ".");
        
    }

    // View courses that a student has enrolled in
    public void viewStudentCourses() {
        
        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

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
                System.out.println("- " + " (" + course.getCourseID() + ")");
            }
        }
    }

    // Cancel a course enrollment for a student
    public void cancelEnrollment() {
       

        System.out.println("Enter Student ID:");
        String studentID = validator.getValidStudentID();

        Enrollment enrollment = enrollmentMap.get(studentID);
        if (enrollment == null) {
            System.out.println("Student not found or no enrollment record.");
            return;
        }
        
        if (enrollment.getCourseList().isEmpty()) {
            System.out.println("Student has no enrolled courses to cancel.");
            return;
        }

        System.out.println("Enter Course ID to cancel:");
        String courseID = validator.getValidCourseCode();

        boolean found = false;
        Iterator<Course> iterator = enrollment.getCourseList().iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseID().equals(courseID)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");
            System.out.println("Course enrollment cancelled for student " + studentID + ".");
        } else {
            System.out.println("The student is not enrolled in the course ID: " + courseID + ".");
        }
    }

    // List all enrollments for all students
    public void listAllEnrollments() {
        if (enrollmentMap.isEmpty()) {
            System.out.println("No enrollment records found.");
            return;
        }
        
        
        System.out.println("All Enrollments:");
        for (Enrollment enrollment : enrollmentMap.values()) {
            System.out.println("----------------------------");
            System.out.println("Student ID: " + enrollment.getStudentID());
            for (Course course : enrollment.getCourseList()) {
                System.out.println("- " + " (" + course.getCourseID() + ")");
                
            }
        }
    }
    // Save enrollment map to file
    private void saveEnrollmentsToFile() {
        enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");
    }

    // Static method to remove enrollments for a deleted student
    public static void removeEnrollmentForDeletedStudent(String studentID) {
        if (enrollmentMap.containsKey(studentID)) {
            enrollmentMap.remove(studentID);
            enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");
        }
    }


    // Static method to remove a course from all students when the course is deleted
    public static void removeCourseFromAllEnrollments(String courseID) {
        boolean changed = false;
        for (Enrollment enrollment : enrollmentMap.values()) {
            if (enrollment.getCourseList().removeIf(course -> course.getCourseID().equals(courseID))) {
                changed = true;
            }
        }
        if (changed) {
            enrollmentDatabase.writeEnrollmentsToFile(new ArrayList<>(enrollmentMap.values()), "enrollments.txt");
        }
    }
}