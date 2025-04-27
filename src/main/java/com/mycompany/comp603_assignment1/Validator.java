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

    // 학생 ID가 유효한지 확인하는 메서드
    public boolean validateStudentID(String studentID) {
        // 학생 ID는 8자리 숫자여야 한다고 가정
        String regex = "^[0-9]{8}$";
        return studentID.matches(regex);
    }

    // 과목 코드가 유효한지 확인하는 메서드
    public boolean validateCourseCode(String courseCode) {
        // 과목 코드는 알파벳과 숫자가 조합된 6자리 문자열이어야 한다고 가정 (예: CS101, MATH201)
        String regex = "^[A-Za-z]{4}[0-9]{3}$";
        return courseCode.matches(regex);
    }

    // 학생 이름이 유효한지 확인하는 메서드
    public boolean validateName(String name) {
        // 이름은 공백을 포함하지 않는 알파벳만 허용
        String regex = "^[A-Za-z]+$"; 
        return name.matches(regex);
    }

    // 학생 전공이 유효한지 확인하는 메서드
    public boolean validateMajor(String major) {
        // 전공은 문자로만 이루어져 있다고 가정 (공백 포함하지 않음)
        String regex = "^[A-Za-z\\s]+$";
        return major.matches(regex);
    }

    // 숫자 입력이 올바른지 확인하는 메서드 (예: 학점, 나이 등)
    public boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //method that allows space in String
    public boolean validateCourseName(String courseName) {
    String regex = "^[A-Za-z\\s]+$";  // 알파벳 + 공백 허용
    return courseName.matches(regex);
}

    // 사용자에게 입력을 받았을 때 올바른 학생 ID를 입력할 때까지 반복하는 메서드
    public String getValidStudentID() {
        Scanner scanner = new Scanner(System.in);
        String studentID;
        while (true) {
            System.out.print("Enter Student ID (8 digits): ");
            studentID = scanner.nextLine();
            if (validateStudentID(studentID)) {
                break;
            } else {
                System.out.println("Invalid Student ID. Please enter a valid 8-digit number.");
            }
        }
        return studentID;
    }

    // 사용자에게 입력을 받았을 때 올바른 과목 코드를 입력할 때까지 반복하는 메서드
    public String getValidCourseCode() {
        Scanner scanner = new Scanner(System.in);
        String courseCode;
        while (true) {
            System.out.print("Enter Course Code (e.g., CS101): ");
            courseCode = scanner.nextLine();
            if (validateCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid Course Code. Please enter a valid code (e.g., CS101).");
            }
        }
        return courseCode;
    }

    // 사용자에게 입력을 받았을 때 올바른 이름을 입력할 때까지 반복하는 메서드
    public String getValidName() {
        Scanner scanner = new Scanner(System.in);
        String name;
        while (true) {
            System.out.print("Enter Student Name: ");
            name = scanner.nextLine();
            if (validateName(name)) {
                break;
            } else {
                System.out.println("Invalid Name. Please enter a valid name (alphabetic characters only).");
            }
        }
        return name;
    }

    // 사용자에게 입력을 받았을 때 올바른 전공을 입력할 때까지 반복하는 메서드
    public String getValidMajor() {
        Scanner scanner = new Scanner(System.in);
        String major;
        while (true) {
            System.out.print("Enter Student Major: ");
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
