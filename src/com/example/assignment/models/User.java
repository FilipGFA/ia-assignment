package com.example.assignment.models;

public class User {
    private int id;
    private String guid;
    private String name;

    public User(int id, String guid, String name) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
