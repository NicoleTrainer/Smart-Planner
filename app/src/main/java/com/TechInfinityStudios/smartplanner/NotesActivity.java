package com.TechInfinityStudios.smartplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {
    ImageButton homeButton, plannerButton, toDoListButton, notesButton, studyTimerButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        homeButton = findViewById(R.id.homeButton);
        plannerButton = findViewById(R.id.planner);
        toDoListButton = findViewById(R.id.toDoList);
        notesButton = findViewById(R.id.notes);
        studyTimerButton = findViewById(R.id.studyTimer);
        settingsButton = findViewById(R.id.settingsButton);

        LinearLayout mainMenu = findViewById(R.id.mainMenu);
        mainMenu.setOnClickListener(v -> {
            navigateToNewActivity();
        });



    }
    private void navigateToNewActivity() {
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
    }
}
