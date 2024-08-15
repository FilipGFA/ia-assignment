package com.example.assignment.repositories;

import com.example.assignment.models.User;
import com.example.assignment.utils.DBCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

     public List<User> getAllUsers(){
        List<User> output = new ArrayList<>();
         try {
             statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from SUSERS");
             while (resultSet.next()) {
                 output.add(new User(Integer.parseInt(resultSet.getString("USER_ID")),
                         resultSet.getString("USER_GUID"),
                         resultSet.getString("USER_NAME")));
             }
         } catch(SQLException throwables){
             throwables.printStackTrace();
         }
         return output;
     }

    public void printAll() {
        for(User user : getAllUsers()){
            System.out.println(user);
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
