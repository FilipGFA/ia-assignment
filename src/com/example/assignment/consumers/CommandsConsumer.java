package com.example.assignment.consumers;

import com.example.assignment.models.Command;
import com.example.assignment.models.PoisonPill;

import java.util.concurrent.BlockingQueue;

public class CommandsConsumer implements Runnable{
    private BlockingQueue<Command> commandsQueue;

    public CommandsConsumer(BlockingQueue<Command> commandsQueue) {
        this.commandsQueue = commandsQueue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Command command = commandsQueue.take();
                if (command instanceof PoisonPill) return;
                System.out.println(Thread.currentThread().getName() + " result: " + command.getClass());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
