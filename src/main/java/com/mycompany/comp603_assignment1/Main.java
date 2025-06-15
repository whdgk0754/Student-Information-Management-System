/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;


import javax.swing.*;


/**
 *
 * @author gvsv0
 */



public class Main {
    public static void main(String[] args) {
        //create DB
        DBManager.initializeDatabase();
    // Run GUI inside the Event Dispatch Thread (recommended for Swing applications)
        SwingUtilities.invokeLater(() -> {
            // Create the main application window
            JFrame frame = new JFrame("Student Information Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Set the window size
            frame.setLocationRelativeTo(null); // Center the window on the screen
            

            // Create instances of management classes
            StudentManagement studentManager = new StudentManagement();
            CourseManagement courseManager = new CourseManagement();
            EnrollmentManagement enrollmentManager = new EnrollmentManagement();

            //create instances panel
            EnrollmentPanel enrollmentPanel = new EnrollmentPanel(enrollmentManager, studentManager, courseManager);
            StudentPanel studentPanel = new StudentPanel(studentManager, enrollmentPanel);
            
            // Create a tabbed pane and add all three panels
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Student Management", studentPanel);
            tabbedPane.addTab("Course Management", new CoursePanel(courseManager));
            tabbedPane.addTab("Enrollment Management", enrollmentPanel);

            // Add the tabbed pane to the frame
            frame.add(tabbedPane);

            // Display the GUI window
            frame.setVisible(true);
        });
    }
}