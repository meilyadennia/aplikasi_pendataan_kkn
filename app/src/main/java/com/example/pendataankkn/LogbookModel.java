package com.example.pendataankkn;

public class LogbookModel {
    private int id;
    private String title, date, location, description;

    public LogbookModel(int id, String title, String date, String location, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    public LogbookModel(String title, String date, String location, String description) {
        this(-1, title, date, location, description);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }

    public void setId(int id) { this.id = id; }
}
