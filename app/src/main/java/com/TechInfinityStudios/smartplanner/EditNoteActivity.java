package com.TechInfinityStudios.smartplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditNoteActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_note_dialog);
        db = new DatabaseHelper(this);
        FloatingActionButton backButton = findViewById(R.id.backButton);
        EditText title = findViewById(R.id.noteTitle);
        EditText text = findViewById(R.id.noteText);

        Intent intent = getIntent();

        long id = intent.getLongExtra("note_id". 0);
        String currentTitle = intent.getStringExtra("note_title");
        String currentText = intent.getStringExtra("note_text");


        title.setText(currentTitle);
        text.setText(currentText);

        backButton.setOnClickListener(v -> {
            String noteTitle = title.getText().toString();
            String noteText = text.getText().toString();

            db.deleteNote(id);
            db.updateNote(id, noteTitle, noteText);

            finish(); // Just go back to the previous screen
        });






    }

}
