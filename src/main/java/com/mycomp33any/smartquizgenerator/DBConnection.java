package com.mycomp33any.smartquizgenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // Default XAMPP/MySQL settings. Change "password" if you have one set up!
    private static final String URL = "jdbc:mysql://localhost:3306/smart_quiz_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234"; 

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("MySQL Connected Successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
        return conn;
    }
}