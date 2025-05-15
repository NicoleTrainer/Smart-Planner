package com.TechInfinityStudios.smartplanner;

public class PlannerEvent {
    private String title;
    private String time;
    private String date;
    private long id;
    public PlannerEvent(long id, String title, String time, String date) {
        this.id = id;
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
    public long getId() {
        return id;
        }
    public void setId(long id) {
        this.id = id;
    }
}
