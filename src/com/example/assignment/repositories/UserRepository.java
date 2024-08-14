package com.example.assignment.repositories;

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

    public void addUser(int id, String guid, String name) {
        String sql = "Insert into SUSERS (USER_ID, USER_GUID, USER_NAME) values ("+id+",'"+guid+"','"+name+"')";
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
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
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
