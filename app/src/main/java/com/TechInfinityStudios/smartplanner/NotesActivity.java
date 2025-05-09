package com.TechInfinityStudios.smartplanner;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NotesActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    RecyclerView recyclerView;
    ImageButton addNote;
    NoteAdapter adapter;
    private List<Note> noteList;
    ImageButton homeButton, plannerButton, toDoListButton, notesButton, studyTimerButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);

         dbHelper = new DatabaseHelper(this);
         noteList = new ArrayList<>();
         adapter = new NoteAdapter(noteList, dbHelper);

        homeButton = findViewById(R.id.homeButton);
        plannerButton = findViewById(R.id.planner);
        toDoListButton = findViewById(R.id.toDoList);
        notesButton = findViewById(R.id.notes);
        studyTimerButton = findViewById(R.id.studyTimer);
        settingsButton = findViewById(R.id.settingsButton);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(NotesActivity.this, MainActivity.class);
            startActivity(intent);
        });

        plannerButton.setOnClickListener(v -> {
            // Handle planner button click
            Intent intent = new Intent(NotesActivity.this, PlannerActivity.class);
            startActivity(intent);
        });

        toDoListButton.setOnClickListener(v -> {
            // Handle to-do list button click
            Intent intent = new Intent(NotesActivity.this, ToDoListActivity.class);
            startActivity(intent);
        });

        notesButton.setOnClickListener(v -> {

        });

        studyTimerButton.setOnClickListener(v -> {
            // Handle study timer button click
            Intent intent = new Intent(NotesActivity.this, StudyTimerActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            // Handle settings button click
            Intent intent = new Intent(NotesActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        addNote = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        showNotes();

        addNote.setOnClickListener(v -> addNote());


    }

    private void addNote() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_note_dialog);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

        Button saveButton = dialog.findViewById(R.id.btnSave);
        Button cancelButton = dialog.findViewById(R.id.btnCancel);
        saveButton.setOnClickListener(v -> {

        });
        cancelButton.setOnClickListener(v -> dialog.dismiss());

    }

    private void showNotes() {

    }
}
