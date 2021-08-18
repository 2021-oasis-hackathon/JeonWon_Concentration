package com.example.oasis;

import android.graphics.Bitmap;
import android.media.Image;

public class Recommend {

    private String title;
    private String content;
    private String image;
    private String time;
    private String location;
    private String location1;
    private String location2;

    public Recommend() {}

    public Recommend(String title, String content, String image, String time, String location, String location1, String location2) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
        this.location = location;
        this.location1 = location1;
        this.location2 = location2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }
}
