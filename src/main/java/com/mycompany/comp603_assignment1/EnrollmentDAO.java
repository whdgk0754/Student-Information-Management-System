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
public class EnrollmentDAO {
    
    //add enrollment
    public void addEnrollment(String studentID, String courseID){
        String sql = "INSERT INTO Enrollment (student_id, course_id) VALUES (?,?)";
        try (Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ps.setString(2, courseID);
            ps.executeUpdate();
            System.out.println("Enrollment added.");
            
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
    //cancel enrollment
    public void cancelEnrollment(String studentID, String courseID){
        String sql = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
        
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, studentID);
            ps.setString(2, courseID);
            
            int rowsAffects = ps.executeUpdate();
            
            if(rowsAffects > 0){
                System.out.println("Enrollment deleted for student ID " + studentID + " and course ID " + courseID);
            }else{
                System.out.println("No enrollment found for student ID " + studentID + " and course ID " + courseID);
            }
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    //return all List of enrollment from DB
    public List<Enrollment> getAllEnrollments(){
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment";
        
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                String studentID = rs.getString("student_id");
                String courseID = rs.getString("course_id");
                
                Enrollment enrollment = new Enrollment(studentID, courseID);
                enrollments.add(enrollment);
            }

        }catch (SQLException e) {
                e.printStackTrace();
            }
        return enrollments;
    }
    //get enrollment by studentid
    public List<Enrollment> getEnrollmentByStudent(String studentID){
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollment WHERE student_id = ?";
        
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, studentID);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String courseID = rs.getString("course_id");
                enrollments.add(new Enrollment(studentID, courseID));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return enrollments;
    }
    //check duplicated
    public boolean isEnrolled(String studentID, String courseID){
        String sql = "SELECT 1 FROM Enrollment WHERE student_id = ? AND course_id = ?";
    try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, studentID);
        ps.setString(2, courseID);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // return true when enrolled
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }
    //delete all enrollment related to student
    public void deleteAllByStudent(String studentID){
         String sql = "DELETE FROM Enrollment WHERE student_id = ?";
         try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, studentID);
        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("All enrollments deleted for student ID " + studentID);
        } else {
            System.out.println("No enrollments found for student ID " + studentID);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    //delete all enrollment related to course
    public void deleteAllByCourse(String courseID){
        String sql = "DELETE FROM Enrollment WHERE course_id = ?";
        
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, courseID);
        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("All enrollments deleted for course ID " + courseID);
        } else {
            System.out.println("No enrollments found for course ID " + courseID);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    } 
    
    

    

