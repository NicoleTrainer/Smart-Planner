package com.TechInfinityStudios.smartplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "planner.db";
    private static final int DATABASE_VERSION = 18;
    private static final String TABLE_EVENTS = "events";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE events(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, time TEXT, date TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, text TEXT)");
        sqLiteDatabase.execSQL("CREATE TABlE toDoList(id INTEGER PRIMARY KEY AUTOINCREMENT, task TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE timer(id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT)");

     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS notes");
        db.execSQL("DROP TABLE IF EXISTS toDoList");
        db.execSQL("DROP TABLE IF EXISTS timer");
        onCreate(db);
    }

    public long insertEvent(String title, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("time", time);
        values.put("date", date);

        long id = db.insert("events", null, values);


        db.close();
        return id;
    }
    public void deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("events", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateEvent(Long id, String title, String time, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("time", time);
        values.put("date", date);
        db.update("events", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public List<PlannerEvent> getAllEvents(String date) {
        List<PlannerEvent> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM events WHERE date = ? ORDER BY time", new String[]{date});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                String eventDate = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                events.add(new PlannerEvent(id, title, time, eventDate)); // Pass the id
            }
            cursor.close();
        }
        db.close();
        return events;
    }

    public long insertNote(String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("text", text);

        long id = db.insert("notes", null, values);


        db.close();
        return id;
    }

    public void deleteNote(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notes", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateNote(Long id, String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("text", text);
        db.update("notes", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes ORDER BY title", null);


            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String text = cursor.getString(cursor.getColumnIndexOrThrow("text"));

                notes.add(new Note(id, title, text)); // Pass the id
            }
            cursor.close();

        db.close();
        return notes;
    }

}