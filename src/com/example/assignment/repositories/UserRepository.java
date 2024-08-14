package com.example.assignment.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserRepository {

    private final Connection connection;

    public UserRepository() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test");
    }
}
