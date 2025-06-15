/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */


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
}