package com.example.assignment;

import com.example.assignment.consumers.CommandsConsumer;
import com.example.assignment.models.Command;
import com.example.assignment.models.DeleteAll;
import com.example.assignment.models.PoisonPill;
import com.example.assignment.models.PrintAll;
import com.example.assignment.producers.CommandsProducer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainRunner {
    public static void main(String[] args) {
        /*String jdbcURL = "jdbc:h2:mem:test";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connected to H2 in-memory database.");*/
        List<Command> commands = new ArrayList<>();
        commands.add(new PrintAll());
        commands.add(new DeleteAll());
        commands.add(new PoisonPill());
        BlockingQueue<Command> queue = new LinkedBlockingQueue<>();
        new Thread(new CommandsProducer(queue, commands)).start();
        new Thread(new CommandsConsumer(queue)).start();
    }


}
