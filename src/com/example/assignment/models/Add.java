package com.example.assignment.models;

public class Add extends Command{
    private int id;
    private String guid;
    private String name;

    public Add(int id, String guid, String name) {
        this.id = id;
        this.guid = guid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getName() {
        return name;
    }
}
