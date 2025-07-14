package com.example.pendataankkn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LogbookDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "kknlog.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_LOGBOOK = "logbook";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_DATE = "date";
    public static final String COL_LOCATION = "location";
    public static final String COL_DESCRIPTION = "description";

    public LogbookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LOGBOOK + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_DATE + " TEXT, " +
                COL_LOCATION + " TEXT, " +
                COL_DESCRIPTION + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGBOOK);
        onCreate(db);
    }
}
