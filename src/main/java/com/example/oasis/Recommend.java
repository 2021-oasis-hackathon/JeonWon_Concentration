package com.example.oasis;

import android.graphics.Bitmap;

public class Recommend {

    private String place;
    private String context;
    private Bitmap bitmap;
    private String likeCount;

    public Recommend() {}

    public Recommend(String place, String context, Bitmap bitmap, String likeCount) {
        this.place = place;
        this.context = context;
        this.bitmap = bitmap;
        this.likeCount = likeCount;
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

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
