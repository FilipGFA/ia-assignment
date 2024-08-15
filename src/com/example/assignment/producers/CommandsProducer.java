package com.example.assignment.producers;

import com.example.assignment.models.Command;
import com.example.assignment.models.PoisonPill;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class CommandsProducer implements Runnable{
    private BlockingQueue<Command> commandsQueue;
    private List<Command> commandList;
    private int poisonPills;

    public CommandsProducer(BlockingQueue<Command> commandsQueue, List<Command> commandList, int poisonPills) {
        this.commandsQueue = commandsQueue;
        this.commandList = commandList;
        this.poisonPills = poisonPills;
    }

    @Override
    public void run() {
        try {
            processCommands();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void processCommands() throws InterruptedException {
        for (int i = 0; i < commandList.size(); i++) {
            commandsQueue.add(commandList.get(i));
        }
        for (int i=0; i<poisonPills; i++){
            commandsQueue.add(new PoisonPill());
        }
    }
}
