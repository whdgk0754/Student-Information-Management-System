/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */
import java.util.Scanner;

public class Validator {
    
    // Validates that the student ID is exactly 8 digits
    public boolean validateStudentID(String studentID) {
        String regex = "^[0-9]{8}$";
        return studentID.matches(regex);
    }
    
    // Validates that the course code follows the pattern: 4 letters + 3 digits (e.g., COMP603)
    public boolean validateCourseCode(String courseCode) {
        String regex = "^[A-Za-z]{4}[0-9]{3}$";
        return courseCode.matches(regex);
    }
    
    // Validates that the name contains only alphabetic characters and spaces
    public boolean validateName(String name) {
        String regex = "^[A-Za-z\\s]+$";
        return name.matches(regex);
    }
    
    // Validates that the major contains only alphabetic characters and spaces
    public boolean validateMajor(String major) {
        String regex = "^[A-Za-z\\s]+$";
        return major.matches(regex);
    }
    
    // Checks whether a given string is a valid integer
    public boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Validates that the course name contains only alphabetic characters and spaces
    public boolean validateCourseName(String courseName) {
        String regex = "^[A-Za-z\\s]+$";
        return courseName.matches(regex);
    }

    // Repeatedly prompts the user for a valid student ID
    public String getValidStudentID() {
        Scanner scanner = new Scanner(System.in);
        String studentID;
        while (true) {
            studentID = scanner.nextLine();
            if (validateStudentID(studentID)) {
                break;
            } else {
                System.out.println("Invalid Student ID. Please enter a valid 8-digit number.");
            }
        }
        return studentID;
    }
    
    // Repeatedly prompts the user for a valid course code
    public String getValidCourseCode() {
        Scanner scanner = new Scanner(System.in);
        String courseCode;
        while (true) {
            courseCode = scanner.nextLine();
            if (validateCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid Course Code. Please enter a valid code (e.g., COMP603).");
            }
        }
        return courseCode;
    }
    
    // Repeatedly prompts the user for a valid name
    public String getValidName() {
        Scanner scanner = new Scanner(System.in);
        String name;
        while (true) {
            name = scanner.nextLine();
            if (validateName(name)) {
                break;
            } else {
                System.out.println("Invalid Name. Please enter a valid name (alphabetic characters only).");
            }
        }
        return name;
    }
    
    // Repeatedly prompts the user for a valid major
    public String getValidMajor() {
        Scanner scanner = new Scanner(System.in);
        String major;
        while (true) {
            major = scanner.nextLine();
            if (validateMajor(major)) {
                break;
            } else {
                System.out.println("Invalid Major. Please enter a valid major (alphabetic characters only).");
            }
        }
        return major;
    }
}
