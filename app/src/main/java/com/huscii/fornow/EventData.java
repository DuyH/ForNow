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

    public void setLate() {
        if ((new Date()).getTime() > (created.getTime() + (time * 60 * 1000))) {
            this.late = true;
        }
    }
}
