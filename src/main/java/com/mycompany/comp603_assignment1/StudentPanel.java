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
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class StudentPanel extends JPanel {
    private final IntStudentManagement studentManagement;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, majorField;

    public StudentPanel(IntStudentManagement studentManagement) {
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

                if (!studentManagement.getValidator().validateStudentID(id)) {
                    showMessage("Invalid student ID format.");
                    return;
                }
                if (!studentManagement.getValidator().validateName(name)) {
                    showMessage("Invalid student name.");
                    return;
                }
                if (!studentManagement.getValidator().validateMajor(major)) {
                    showMessage("Invalid major.");
                    return;
                }

                try {
                    Student s = new Student(id, name, major);
                    studentManagement.addStudent(s);
                    showMessage("Student added successfully.");
                    loadStudentData();
                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage());
                }
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

                if (id.isEmpty() || name.isEmpty() || major.isEmpty()) {
                    showMessage("Please fill all fields.");
                    return;
                }

                try {
                    Student updatedStudent = new Student(id, name, major);
                    studentManagement.updateStudent(updatedStudent);
                    showMessage("Student updated successfully.");
                    loadStudentData();
                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage());
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

                try {
                    studentManagement.deleteStudent(id);
                    showMessage("Student deleted successfully.");
                    loadStudentData();
                } catch (IllegalArgumentException ex) {
                    showMessage(ex.getMessage());
                }
            }
        });

        // Table click loads data into fields
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int row = studentTable.getSelectedRow();
                if (row >= 0) {
                    idField.setText(tableModel.getValueAt(row, 0).toString());
                    nameField.setText(tableModel.getValueAt(row, 1).toString());
                    majorField.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });
    }

    // Load student data into table
    private void loadStudentData() {
        tableModel.setRowCount(0);
        List<Student> students = studentManagement.getStudentList();
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.getStudentID(), s.getName(), s.getMajor()});
        }
    }

    // Show message dialog
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}



