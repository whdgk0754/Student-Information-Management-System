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
public class Main {
    public static void main(String[] args) {
        ArrayList <String> course = new ArrayList<>();
        course.add("a");
        course.add("b");
        //Student student = new Student("1","shawn","m",course);
        
        StudentManagement sm = new StudentManagement();
        sm.addStudent();
        sm.updateStudent("2");
    }
}
