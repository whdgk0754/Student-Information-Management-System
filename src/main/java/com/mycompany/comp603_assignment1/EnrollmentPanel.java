/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;

/**
 *
 * @author gvsv0
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EnrollmentPanel extends JPanel {
    private final IntEnrollmentManagement enrollmentManagement;
    private final IntStudentManagement studentManagement;
    private final IntCourseManagement courseManagement;
    private JTable enrollmentTable;
    private DefaultTableModel tableModel;
    private JTextField studentIDField, courseIDField;

    public EnrollmentPanel(IntEnrollmentManagement enrollmentManagement, IntStudentManagement studentManagement, IntCourseManagement courseManagement) {
        this.enrollmentManagement = enrollmentManagement;
        this.studentManagement = studentManagement;
        this.courseManagement = courseManagement;

        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        inputPanel.add(studentIDField);
        
        inputPanel.add(new JLabel("Course ID:"));
        courseIDField = new JTextField();
        inputPanel.add(courseIDField);
        
        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Student ID", "Course ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        enrollmentTable = new JTable(tableModel);
        add(new JScrollPane(enrollmentTable), BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton enrollButton = new JButton("Enroll");
        JButton cancelButton = new JButton("Cancel Enrollment");
        JButton viewButton = new JButton("View Student Courses");
        
        buttonPanel.add(enrollButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(viewButton);
        
        add(buttonPanel, BorderLayout.SOUTH);

        // Load existing enrollment data
        loadEnrollmentData();

        // Enroll button
        enrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText().trim();
                String courseID = courseIDField.getText().trim();

                if (studentID.isEmpty() || courseID.isEmpty()) {
                    showMessage("Please fill all fields.");
                    return;
                }

                if (studentManagement.searchStudentObject(studentID) == null) {
                    showMessage("Student not found.");
                    return;
                }

                if (courseManagement.searchCourse(courseID) == null) {
                    showMessage("Course not found.");
                    return;
                }

                try {
                    enrollmentManagement.enrollCourseToStudent(studentID, courseID);
                    showMessage("Enrollment successful.");
                    loadEnrollmentData();
                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage());
                }
            }
        });

        // Cancel button
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText().trim();
                String courseID = courseIDField.getText().trim();

                if (studentID.isEmpty() || courseID.isEmpty()) {
                    showMessage("Please fill all fields.");
                    return;
                }

                try {
                    enrollmentManagement.cancelEnrollment(studentID, courseID);
                    showMessage("Enrollment cancelled.");
                    loadEnrollmentData();
                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage());
                }
            }
        });

        // View button
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = studentIDField.getText().trim();

                if (studentID.isEmpty()) {
                    showMessage("Please enter a Student ID.");
                    return;
                }

                List<Course> courses = enrollmentManagement.getEnrolledCourses(studentID);
                if (courses == null || courses.isEmpty()) {
                    showMessage("No courses enrolled for this student.");
                    return;
                }

                StringBuilder sb = new StringBuilder("Courses enrolled:\n");
                for (Course c : courses) {
                    sb.append(c.getCourseID()).append(" - ").append(c.getCourseName()).append("\n");
                }
                showMessage(sb.toString());
            }
        });
        
        // Table row click to fill fields
        enrollmentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int row = enrollmentTable.getSelectedRow();
                if (row >= 0) {
                    studentIDField.setText(tableModel.getValueAt(row, 0).toString());
                    courseIDField.setText(tableModel.getValueAt(row, 1).toString());
                }
            }
        });
    }

    // Load all enrollments into table
    private void loadEnrollmentData() {
        tableModel.setRowCount(0);
        Map<String, Enrollment> enrollmentMap = enrollmentManagement.getEnrollmentMap();
        for (Map.Entry<String, Enrollment> entry : enrollmentMap.entrySet()) {
            String studentID = entry.getKey();
            for (Course c : entry.getValue().getCourseList()) {
                tableModel.addRow(new Object[]{studentID, c.getCourseID()});
            }
        }
    }

    // Show message
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    //refreshTable
    public void refreshTable() {
    loadEnrollmentData();
}
}

