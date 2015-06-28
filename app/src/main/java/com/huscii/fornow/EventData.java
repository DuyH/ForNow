package com.huscii.fornow;

import java.util.Date;

public class EventData {
    private String title;
    private String tag;
    private String desc;
    private int time;
    private int duration;
    private Date created;
    private boolean late;
    private double lat;
    private double lon;

    public EventData(String title, String tag, String desc, int time, int duration, double lat, double lon) {
        this.title = title;
        this.tag = tag;
        this.desc = desc;
        this.time = time;
        this.duration = duration;
        this.created = new Date();
        this.late = false;
        this.lat = lat;
        this.lon = lon;
    }
    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getDesc() {
        return desc;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isLate() {
        return late;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
    public void setLate() {
        if ((new Date()).getTime() > (created.getTime() + (time * 60 * 1000))) {
            this.late = true;
        }
    }
}
