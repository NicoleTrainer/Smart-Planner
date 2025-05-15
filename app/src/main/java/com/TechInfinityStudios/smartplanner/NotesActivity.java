package com.TechInfinityStudios.smartplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

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

        addNote = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.notesRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        showNotes();

        addNote.setOnClickListener(v -> addNote());

        adapter.setOnNoteLongClickListener((note, position) -> {
            new AlertDialog.Builder(NotesActivity.this) // Use 'getContext()' if you're in a Fragment
                    .setTitle("Delete Note")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        // Delete the note
                        dbHelper.deleteNote(note.getId());
                        noteList.remove(position);
                        adapter.notifyItemRemoved(position);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();


        });
        adapter.setNoteOnClickListener((note, position) -> {
            Intent intent = new Intent(NotesActivity.this, EditNoteActivity.class);
            intent.putExtra("note_id", note.getId());
            intent.putExtra("note_title", note.getTitle());
            intent.putExtra("note_text", note.getText());
            noteList.clear();
            startActivity(intent);
        });
        showNotes();

    }

    private void addNote() {
        Intent intent = new Intent(NotesActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void showNotes() {
        noteList.clear();
        noteList.addAll(dbHelper.getAllNotes());
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        super.onResume();
        showNotes(); // reload notes list
    }

}
