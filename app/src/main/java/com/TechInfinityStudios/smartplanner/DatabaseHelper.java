package com.TechInfinityStudios.smartplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "planner.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_EVENTS = "events";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE events(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, time TEXT, date TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT)");
        sqLiteDatabase.execSQL("CREATE TABlE toDoList(id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE timer(id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT)");

     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS events ");
        onCreate(sqLiteDatabase);
    }

    public void insertEvent(String title, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("time", time);
        values.put("date", date);

        db.insert("events", null, values);
        db.close();
    }
    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("events", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateEvent(int id, String event, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("event", event);
        values.put("time", time);
        values.put("date", date);
        db.update("events", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public List<PlannerEvent> getAllEvents(String date) {
        List<PlannerEvent> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM events WHERE date = ? ORDER BY time", new String[]{date});

        if(cursor != null) {
            while (cursor.moveToNext()) {
                events.add(new PlannerEvent(
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("time")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date"))
                ));
            }
            cursor.close();
        }
        db.close();
        return events;
    }

}