/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.comp603_assignment1;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author jonghapark
 */
public class DBManager {
    private static final String DB_URL = "jdbc:derby:studentDB;create=true";
    
    //link to DB and return Connection
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL);
    }
    
    public static void initializeDatabase(){
        try(Connection conn = getConnection(); Statement st = conn.createStatement() ){
            //create Student table
            st.executeUpdate("""
               CREATE TABLE Student (
                id INT PRIMARY KEY,
                name VARCHAR(100), 
                major VARCHAR(100)             
                             
                )
                """);
            //create Course table
            st.executeUpdate("""
                CREATE TABLE Course (
                course_id VARCHAR(10) PRIMARY KEY,
                title VARCHAR(100),
                credits INT
                             )
                             
                             
                             """);
            //create Enrollment table
            st.executeUpdate("""
                CREATE TABLE Enrollment (
                student_id INT,
                course_id VARCHAR(10),
                PRIMARY KEY(student_id, course_id), 
                CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Student(id),
                CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES Course(course_id)            
                             
                                          )
                             
                             """);
            System.out.println("Database initialized successfully.");
            
        }catch(SQLException e){
            
            if(!e.getSQLState().equals("X0Y32")){ //code X0Y32, table exists
                e.printStackTrace();
            }
        }
    }
}
