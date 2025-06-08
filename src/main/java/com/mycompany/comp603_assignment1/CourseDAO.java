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
public class CourseDAO {
    public void addCourse(Course course){
        String sql = "INSERT INTO Course (id, title, credits) VALUES (?,?,?)";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, course.getCourseID());
            ps.setString(2, course.getCourseName());
            ps.setInt(3, course.getCredit());
            ps.executeUpdate();
            System.out.println("Course added.");
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deleteCourse(Course course){
        String sql = "DELETE FROM Course WHERE id = ?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, course.getCourseID());
            int rowsAffects= ps.executeUpdate();
            if(rowsAffects > 0){
                System.out.println("Course deleted.");
            }else{
                System.out.println("No course ID found with " + course.getCourseID());
            }
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updateCourse(Course course){
        String sql = "UPDATE Course SET title = ?, credits = ? WHERE id = ?";
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getCredit());
            ps.setString(3, course.getCourseID());
            int rowsAffects= ps.executeUpdate();
            if(rowsAffects > 0){
                System.out.println("Course updated.");
            }else{
                System.out.println("No course ID found with " + course.getCourseID());
            }
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Course searchCourse(String courseID){
        String sql = "SELECT * FROM Course WHERE id = ?";
        try (Connection conn = DBManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, courseID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                int credits = rs.getInt("credits");
                return new Course(id, title, credits);
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }
    
    public List<Course> getAllCourses(){
        String sql = "SELECT * FROM Course";
        List<Course> courses = new ArrayList<>();
        
        try(Connection conn = DBManager.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           
           while(rs.next()){
               String id = rs.getString("id");
               String title = rs.getString("title");
               int credits = rs.getInt("credits");
               
               Course course = new Course(id, title, credits);
               courses.add(course);
               
           }
           
           
           }catch (SQLException e){
            e.printStackTrace();
        }
        return courses;
    }
}
