package com.example.pendataankkn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LogbookDAO {
    private SQLiteDatabase db;

    public LogbookDAO(Context context) {
        db = new LogbookDatabaseHelper(context).getWritableDatabase();
    }

    public long insert(LogbookModel logbook) {
        ContentValues values = new ContentValues();
        values.put("title", logbook.getTitle());
        values.put("date", logbook.getDate());
        values.put("location", logbook.getLocation());
        values.put("description", logbook.getDescription());
        values.put("imageUri", logbook.getImageUri());
        return db.insert("logbook", null, values);
    }

    public ArrayList<LogbookModel> getAll() {
        ArrayList<LogbookModel> list = new ArrayList<>();
        Cursor cursor = db.query("logbook", null, null, null, null, null, "id DESC");
        while (cursor.moveToNext()) {
            list.add(new LogbookModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("location")),
                    cursor.getString(cursor.getColumnIndexOrThrow("description")),
                    cursor.getString(cursor.getColumnIndexOrThrow("imageUri"))
            ));
        }
        return list;
    }

    public void update(LogbookModel logbook) {
        ContentValues values = new ContentValues();
        values.put("title", logbook.getTitle());
        values.put("date", logbook.getDate());
        values.put("location", logbook.getLocation());
        values.put("description", logbook.getDescription());
        db.update("logbook", values, "id = ?", new String[]{String.valueOf(logbook.getId())});
    }

    public void delete(int id) {
        db.delete("logbook", "id = ?", new String[]{String.valueOf(id)});
    }
}

