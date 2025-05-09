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
    private static final String name = "planner.db";
    private static final int version = 1;
    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events(id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT, time TEXT, date TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS toDoList(id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS timer(id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT)");

     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertEvent(String title, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("time", time);
        values.put("date", date);
        long id = db.insert("events", null, values);
        values.put("id", id);
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