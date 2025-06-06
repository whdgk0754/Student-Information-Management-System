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

public class CoursePanel extends JPanel {
    private CourseManagement courseManagement;
    private JTable courseTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, creditField;

    public CoursePanel(CourseManagement courseManagement) {
        this.courseManagement = courseManagement;
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Course ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Course Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Credit:"));
        creditField = new JTextField();
        inputPanel.add(creditField);
        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Course ID", "Course Name", "Credit"};
        tableModel = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Course");
        JButton updateButton = new JButton("Update Course");
        JButton deleteButton = new JButton("Delete Course");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load existing courses
        loadCourseData();

        // Add button event
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String creditText = creditField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || creditText.isEmpty()) {
                    showMessage("Please fill all fields.");
                    return;
                }

                if (!courseManagement.getValidator().isValidInteger(creditText)) {
                    showMessage("Credit must be a number.");
                    return;
                }

                if (courseManagement.searchCourse(id) != null) {
                    showMessage("Course ID already exists.");
                    return;
                }

                Course course = new Course(id, name, Integer.parseInt(creditText));
                courseManagement.getCourseList().add(course); // Add to list
                courseManagement.getCourseMap().put(id, course); // Add to map
                courseManagement.getCourseDatabase().writeCoursesToFile(courseManagement.getCourseList(), "courses.txt"); // Save to file
                showMessage("Course added successfully.");
                loadCourseData();
            }
        });

        // Update button event
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseTable.getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Please select a course to update.");
                    return;
                }

                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String creditText = creditField.getText().trim();

                Course course = courseManagement.searchCourse(id);
                if (course != null) {
                    course.setCourseName(name);
                    course.setCredit(Integer.parseInt(creditText));
                    courseManagement.getCourseDatabase().writeCoursesToFile(courseManagement.getCourseList(), "courses.txt"); // Save to file
                    showMessage("Course updated successfully.");
                    loadCourseData();
                } else {
                    showMessage("Course ID not found.");
                }
            }
        });

        // Delete button event
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = courseTable.getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Please select a course to delete.");
                    return;
                }

                String id = tableModel.getValueAt(selectedRow, 0).toString();
                Course course = courseManagement.searchCourse(id);
                if (course != null) {
                    courseManagement.getCourseList().remove(course); // Remove from list
                    courseManagement.getCourseMap().remove(id); // Remove from map
                    courseManagement.getCourseDatabase().writeCoursesToFile(courseManagement.getCourseList(), "courses.txt"); // Save to file
                    showMessage("Course deleted successfully.");
                    loadCourseData();
                } else {
                    showMessage("Course not found.");
                }
            }
        });

        // Table click loads data into fields
        courseTable.getSelectionModel().addListSelectionListener(event -> {
            int row = courseTable.getSelectedRow();
            if (row >= 0) {
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                creditField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });
    }

    // Load course data into table
    private void loadCourseData() {
        tableModel.setRowCount(0);
        List<Course> courses = courseManagement.getCourseList(); // Use getter
        for (Course c : courses) {
            tableModel.addRow(new Object[]{c.getCourseID(), c.getCourseName(), c.getCredit()});
        }
    }

    // Show message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
