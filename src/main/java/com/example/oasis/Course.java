package com.example.oasis;

import android.graphics.Bitmap;

public class Course {

    private String place;
    private String context;
    private Bitmap bitmap;

    public Course() {}

    public Course(String place, String context, Bitmap bitmap) {
        this.place = place;
        this.context = context;
        this.bitmap = bitmap;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
