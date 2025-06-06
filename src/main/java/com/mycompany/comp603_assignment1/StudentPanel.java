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

public class StudentPanel extends JPanel {
    private StudentManagement studentManagement;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, majorField;

    public StudentPanel(StudentManagement studentManagement) {
        this.studentManagement = studentManagement;
        setLayout(new BorderLayout());

        // Top input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Major:"));
        majorField = new JTextField();
        inputPanel.add(majorField);
        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Student ID", "Name", "Major"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load existing students
        loadStudentData();

        // Add button event
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String major = majorField.getText().trim();

                if (id.isEmpty() || name.isEmpty() || major.isEmpty()) {
                    showMessage("Please fill all fields.");
                    return;
                }

                // Check if ID already exists
                if (studentManagement.searchStudentObject(id) != null) {
                    showMessage("Student ID already exists.");
                    return;
                }

                // [Fixed] Add student by direct manipulation of fields
                Student s = new Student(id, name, major);
                studentManagement.getStudentList().add(s); // [Fixed] Access via getter
                studentManagement.getStudentMap().put(id, s); // [Fixed] Access via getter
                studentManagement.getStudentDatabase().writeStudentsToFile(studentManagement.getStudentList(), "students.txt"); // [Fixed] Save using getter

                showMessage("Student added successfully.");
                loadStudentData();
            }
        });

        // Update button event
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Please select a student to update.");
                    return;
                }

                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String major = majorField.getText().trim();

                Student student = studentManagement.searchStudentObject(id);
                if (student != null) {
                    student.setName(name);
                    student.setMajor(major);
                    studentManagement.getStudentDatabase().writeStudentsToFile(studentManagement.getStudentList(), "students.txt"); // [Fixed] Save
                    showMessage("Student updated successfully.");
                    loadStudentData();
                } else {
                    showMessage("Student ID not found.");
                }
            }
        });

        // Delete button event
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow == -1) {
                    showMessage("Please select a student to delete.");
                    return;
                }
                String id = tableModel.getValueAt(selectedRow, 0).toString();
                Student student = studentManagement.searchStudentObject(id);
                if (student != null) {
                    studentManagement.getStudentList().remove(student); // [Fixed] Remove from list
                    studentManagement.getStudentMap().remove(id); // [Fixed] Remove from map
                    studentManagement.getStudentDatabase().writeStudentsToFile(studentManagement.getStudentList(), "students.txt"); // [Fixed] Save
                    showMessage("Student deleted successfully.");
                    loadStudentData();
                } else {
                    showMessage("Student not found.");
                }
            }
        });

        // Table click loads data into fields
        studentTable.getSelectionModel().addListSelectionListener(event -> {
            int row = studentTable.getSelectedRow();
            if (row >= 0) {
                idField.setText(tableModel.getValueAt(row, 0).toString());
                nameField.setText(tableModel.getValueAt(row, 1).toString());
                majorField.setText(tableModel.getValueAt(row, 2).toString());
            }
        });
    }

    // Load student data into table
    private void loadStudentData() {
        tableModel.setRowCount(0);
        for (Student s : studentManagement.getStudentList()) { // [Fixed] Accessing internal list
            tableModel.addRow(new Object[]{s.getStudentID(), s.getName(), s.getMajor()});
        }
    }

    // Show message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}



