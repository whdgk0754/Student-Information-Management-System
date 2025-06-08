/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author jonghapark
 */
public class StudentDAO {
    
   
    //Add student to DB
    public void addStudent(Student student){
        String sql = "INSERT INTO Student (id, name, major) VALUES (?, ?, ?)";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getName());
            ps.setString(3, student.getMajor());
            ps.executeUpdate();
            System.out.println("Student added.");
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Student> getAllStudent(){
        String sql = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();
        
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               String id = rs.getString("id");
               String name = rs.getString("name");
               String major = rs.getString("major");
               
               Student student = new Student(id, name, major);
               students.add(student);
           }
            
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        
        return students;
    }
    public void deleteStudent(Student student){
        String sql = "DELETE FROM Student WHERE id = ?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, student.getStudentID());
        int rowsAffects = ps.executeUpdate();
            System.out.println("Student deleted.");
            
        if(rowsAffects < 0) {
            System.out.println("Error");
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateStudent(Student student){
        String sql = "UPDATE Student SET name = ?, major = ? WHERE id = ?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, student.getName());
            ps.setString(2, student.getMajor());
            ps.setString(3, student.getStudentID());
            
            int rowsAffects = ps.executeUpdate();
            
        if(rowsAffects > 0) {
             System.out.println("Student information updated.");
        }else{
            System.out.println("No student ID found with " + student.getStudentID());
        }
        
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Student searchStudentFromDB(String studentID ){
        String sql = "SELECT * FROM Student WHERE id = ?";
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, studentID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                String major = rs.getString("major");
                return new Student(studentID, name, major);
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null; //not found
}
}

