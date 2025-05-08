package com.TechInfinityStudios.smartplanner;

public class PlannerEvent {
    private String title;
    private String time;
    private String date;
    private int id;
    public PlannerEvent(String title, String time, String date) {
        this.title = title;
        this.time = time;
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public String getTime() {
        return time;
    }
    public int getId() {
        return id;
        }
    public void setId(int id) {
        this.id = id;
    }
}
