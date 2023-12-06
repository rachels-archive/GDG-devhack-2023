package com.example.hackathon.models;

public class ResourceModel {
    String name;
    String description;

    String type;

    public ResourceModel(String name, String description ) {
        this.name = name;
        this.description = description;
    }

    public ResourceModel(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() { return type; }


}




