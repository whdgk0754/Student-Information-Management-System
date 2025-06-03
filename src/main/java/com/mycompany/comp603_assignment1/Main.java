/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

import java.util.ArrayList;



/**
 *
 * @author jonghapark
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // create instances from each Manangement
        StudentManagement studentManagement = new StudentManagement();
        CourseManagement courseManagement = new CourseManagement();
        EnrollmentManagement enrollmentManagement = new EnrollmentManagement();

        //create each database table
        DBManager.initializeDatabase();
        System.out.println("DataBase initalized successfully.");

        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // clean buffer

                switch (choice) {
                    case 1:
                        studentManagement.studentManagementMenu(courseManagement);
                        break;
                    case 2:
                        courseManagement.courseManagementMenu();
                        break;
                    case 3:
                        enrollmentManagement.enrollmentManagementMenu(studentManagement, courseManagement);
                        break;
                    case 4:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clean buffer
            }
        }
    }
}