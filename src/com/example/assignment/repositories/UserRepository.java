package com.example.assignment.repositories;

import com.example.assignment.models.User;
import com.example.assignment.utils.DBCon;

import java.sql.*;

public class UserRepository {

    private Connection connection;
    private Statement statement;

    public boolean openConnection(){
        try {
            connection = DriverManager.getConnection(DBCon.JDBC_URL,DBCon.DB_USERNAME,DBCon.DB_PASSWORD);
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUser(User user) {
        String sql = "Insert into SUSERS (USER_ID, USER_GUID, USER_NAME) values ("
                + user.getId() +",'"
                + user.getGuid() +"','"
                + user.getName() +"')";
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printAll() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * from SUSERS");
            while (resultSet.next()) {
                User user = new User(Integer.parseInt(resultSet.getString("USER_ID")),
                        resultSet.getString("USER_GUID"),
                        resultSet.getString("USER_NAME"));
                System.out.println(user);
            }
        } catch(SQLException throwables){
                throwables.printStackTrace();
        }
    }

    public void deleteAll(){
        try {
            String sql = "DELETE from SUSERS";
            statement = connection.createStatement();
            statement.execute(sql);
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
