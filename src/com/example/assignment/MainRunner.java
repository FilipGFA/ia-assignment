package com.example.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRunner {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:mem:test";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connected to H2 in-memory database.");
    }


}
