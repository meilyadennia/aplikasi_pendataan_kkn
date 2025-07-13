package com.example.pendataankkn;

import android.net.Uri;

public class LogbookEntry {
    public String date;
    public String title;
    public String location;
    public String description;
    public Uri photoUri;

    public LogbookEntry(String date, String title, String location, String description, Uri photoUri) {
        this.date = date;
        this.title = title;
        this.location = location;
        this.description = description;
        this.photoUri = photoUri;
    }
}

