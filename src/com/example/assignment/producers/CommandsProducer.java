package com.example.assignment.producers;

import com.example.assignment.models.Command;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class CommandsProducer implements Runnable{
    private BlockingQueue<Command> commandsQueue;
    private List<Command> commandList;

    public CommandsProducer(BlockingQueue<Command> commandsQueue, List<Command> commandList) {
        this.commandsQueue = commandsQueue;
        this.commandList = commandList;
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
    }
}
