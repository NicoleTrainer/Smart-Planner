package com.TechInfinityStudios.smartplanner;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.graphics.Point;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlannerActivity extends AppCompatActivity {
    String date;
    ImageButton addButton;
    CalendarView calendarView;
    Calendar calendar;
    PlannerAdapter adapter;
    RecyclerView recyclerView;
    private DatabaseHelper dbHelper;
    private List<PlannerEvent> eventList;
    ImageButton homeButton, plannerButton, toDoListButton, notesButton, studyTimerButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        dbHelper = new DatabaseHelper(this);
        eventList = new ArrayList<>();
        adapter = new PlannerAdapter(eventList, dbHelper);

        homeButton = findViewById(R.id.homeButton);
        plannerButton = findViewById(R.id.planner);
        toDoListButton = findViewById(R.id.toDoList);
        notesButton = findViewById(R.id.notes);
        studyTimerButton = findViewById(R.id.studyTimer);
        settingsButton = findViewById(R.id.settingsButton);

        // Navigation Button Click Listeners
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlannerActivity.this, MainActivity.class);
            startActivity(intent);
        });

        toDoListButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlannerActivity.this, ToDoListActivity.class);
            startActivity(intent);
        });

        notesButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlannerActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        studyTimerButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlannerActivity.this, StudyTimerActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlannerActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Calendar View Setup
        calendarView = findViewById(R.id.calendarView);


        // RecyclerView Setup
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerview);
        calendar = Calendar.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        showCalendarEvents();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            showCalendarEvents();
        });



        // Add Button Click Listener
        addButton.setOnClickListener(v -> addCalendarEvent());
    }

    private void addCalendarEvent() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_event_dialog);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText eventName = dialog.findViewById(R.id.eventName);
        EditText eventTime = dialog.findViewById(R.id.EventTime);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        eventTime.setFocusable(false);
        eventTime.setClickable(true);

        // Time Picker Dialog Setup
        eventTime.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    PlannerActivity.this,
                    (view1, hourOfDay, minute) -> {
                        String time;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        time = simpleDateFormat.format(calendar.getTime());
                        eventTime.setText(time);
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    false
            );
            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        });

        // Save Button Click Listener
        btnSave.setOnClickListener(v -> {
            String event = eventName.getText().toString();
            String timeOfEvent = eventTime.getText().toString();
            String date = formatDate(calendar.getTimeInMillis());

            // Validate Input Fields
            if (event.isEmpty() || timeOfEvent.isEmpty()) {
                Toast.makeText(PlannerActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            dbHelper.insertEvent(event, timeOfEvent, date);
            eventList.add(new PlannerEvent(event, timeOfEvent, date));
            adapter.notifyDataSetChanged();
            dialog.dismiss();
            showCalendarEvents();
        });

        // Cancel Button Click Listener
        btnCancel.setOnClickListener(v -> dialog.dismiss());
    }

    private void showCalendarEvents() {
        eventList.clear();
        String date = formatDate(calendar.getTimeInMillis());
        eventList.addAll(dbHelper.getAllEvents(date));
        adapter.notifyDataSetChanged();
    }

    private String formatDate(long dateInMillis) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dateInMillis);
    }
}
