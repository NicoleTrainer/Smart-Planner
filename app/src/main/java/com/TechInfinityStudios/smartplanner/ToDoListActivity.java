package com.TechInfinityStudios.smartplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ToDoListActivity extends AppCompatActivity {
    ImageButton homeButton, plannerButton, toDoListButton, notesButton, studyTimerButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todolist);
        homeButton = findViewById(R.id.homeButton);
        plannerButton = findViewById(R.id.planner);
        toDoListButton = findViewById(R.id.toDoList);
        notesButton = findViewById(R.id.notes);
        studyTimerButton = findViewById(R.id.studyTimer);
        settingsButton = findViewById(R.id.settingsButton);


        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ToDoListActivity.this, MainActivity.class);
            startActivity(intent);
        });

        plannerButton.setOnClickListener(v -> {
            // Handle planner button click
            Intent intent = new Intent(ToDoListActivity.this, PlannerActivity.class);
            startActivity(intent);
        });

        toDoListButton.setOnClickListener(v -> {

        });

        notesButton.setOnClickListener(v -> {
            // Handle notes button click
            Intent intent = new Intent(ToDoListActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        studyTimerButton.setOnClickListener(v -> {
            // Handle study timer button click
            Intent intent = new Intent(ToDoListActivity.this, StudyTimerActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            // Handle settings button click
            Intent intent = new Intent(ToDoListActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
