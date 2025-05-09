package com.TechInfinityStudios.smartplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton homeButton, plannerButton, toDoListButton, notesButton, studyTimerButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        homeButton = findViewById(R.id.homeButton);
        plannerButton = findViewById(R.id.planner);
        toDoListButton = findViewById(R.id.toDoList);
        notesButton = findViewById(R.id.notes);
        studyTimerButton = findViewById(R.id.studyTimer);
        settingsButton = findViewById(R.id.settingsButton);


        homeButton.setOnClickListener(v -> {
        // Already in Home screen, no need to navigate

        });

        plannerButton.setOnClickListener(v -> {
            // Handle planner button click
            Intent intent = new Intent(MainActivity.this, PlannerActivity.class);
            startActivity(intent);
        });

        toDoListButton.setOnClickListener(v -> {
            // Handle to-do list button click
            Intent intent = new Intent(MainActivity.this, ToDoListActivity.class);
            startActivity(intent);
        });

        notesButton.setOnClickListener(v -> {
            // Handle notes button click
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        studyTimerButton.setOnClickListener(v -> {
            // Handle study timer button click
            Intent intent = new Intent(MainActivity.this, StudyTimerActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            // Handle settings button click
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}