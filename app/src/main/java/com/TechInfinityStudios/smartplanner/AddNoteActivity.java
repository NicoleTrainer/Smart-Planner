package com.TechInfinityStudios.smartplanner;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_note_dialog);
        db = new DatabaseHelper(this);
        FloatingActionButton backButton = findViewById(R.id.backButton);
        EditText title = findViewById(R.id.noteTitle);
        EditText text = findViewById(R.id.noteText);

        backButton.setOnClickListener(v -> {
           String noteTitle = title.getText().toString();
           String noteText = text.getText().toString();

           long id = db.insertNote(noteTitle, noteText);
            Intent intent = new Intent(AddNoteActivity.this, NotesActivity.class);
            startActivity(intent);
        });





    }

}
