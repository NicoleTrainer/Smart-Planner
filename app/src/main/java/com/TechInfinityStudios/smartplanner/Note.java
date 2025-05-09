package com.TechInfinityStudios.smartplanner;

public class Note {
    private int id;
    private String title;
    private String text;

    // Constructors, getters, and setters

    public Note( int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;

    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }


}
