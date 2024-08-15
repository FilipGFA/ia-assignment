package com.example.assignment;

import com.example.assignment.consumers.CommandsConsumer;
import com.example.assignment.models.*;
import com.example.assignment.producers.CommandsProducer;
import com.example.assignment.utils.DBCon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainRunner {
    public static void main(String[] args) {
        if(!prepareTable()) return;
        int nConsumers = 2;
        List<Command> commands = new ArrayList<>();
        commands.add(new Add(1, "a1", "Robert"));
        commands.add(new Add(2, "a2", "Martin"));
        commands.add(new PrintAll());
        commands.add(new DeleteAll());
        commands.add(new PrintAll());
        BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
        new Thread(new CommandsProducer(queue, commands, nConsumers)).start();
        for (int i = 0; i < nConsumers; i++){
            new Thread(new CommandsConsumer(queue)).start();
        }
    }

    private static boolean prepareTable(){
        try {
            Connection connection = DriverManager.getConnection(DBCon.JDBC_URL,DBCon.DB_USERNAME,DBCon.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute("drop table if exists SUSERS");
            String sql = "Create table SUSERS (USER_ID int primary key, USER_GUID varchar(50), USER_NAME varchar(50))";
            statement.execute(sql);
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
