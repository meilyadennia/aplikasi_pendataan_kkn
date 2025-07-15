package com.example.pendataankkn;

public class LogbookModel {
    private int id;
    private String title, date, location, description, imageUri;

    public LogbookModel(int id, String title, String date, String location, String description, String imageUri) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
        this.imageUri = imageUri;
    }

    public LogbookModel(String title, String date, String location, String description, String imageUri) {
        this(-1, title, date, location, description, imageUri);
    }
    // Getter & Setter
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getImageUri() { return imageUri; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }
}
