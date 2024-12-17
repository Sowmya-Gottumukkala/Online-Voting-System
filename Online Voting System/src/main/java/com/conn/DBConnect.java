package com.conn;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private static Connection conn;

    public static Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure you have MySQL JDBC driver.
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/votingappj2ee", "root", "password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
