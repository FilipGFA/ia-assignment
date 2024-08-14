package com.example.assignment.consumers;

import com.example.assignment.models.*;
import com.example.assignment.repositories.UserRepository;
import java.util.concurrent.BlockingQueue;

public class CommandsConsumer implements Runnable{
    private BlockingQueue<Command> commandsQueue;
    private UserRepository userRepository;

    public CommandsConsumer(BlockingQueue<Command> commandsQueue) {
        this.commandsQueue = commandsQueue;
        userRepository = new UserRepository();
    }

    @Override
    public void run() {
        if(!userRepository.openConnection()) return;
        try {
            while (true) {
                Command command = commandsQueue.take();
                if (command instanceof PoisonPill){
                    userRepository.closeConnection();
                    return;
                }
                else if(command instanceof Add) addUser((Add)command);
                else if(command instanceof PrintAll) userRepository.printAll();
                else if(command instanceof DeleteAll) userRepository.deleteAll();
            }
        } catch (InterruptedException e) {
            userRepository.closeConnection();
            Thread.currentThread().interrupt();
        }
    }

    private void addUser(Add add){
        userRepository.addUser(new User(add.getId(),add.getGuid(),add.getName()));
    }
}
